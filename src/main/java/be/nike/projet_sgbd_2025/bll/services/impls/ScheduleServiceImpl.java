package be.nike.projet_sgbd_2025.bll.services.impls;

import be.nike.projet_sgbd_2025.bll.services.ScheduleService;
import be.nike.projet_sgbd_2025.dal.repositories.ClassroomRepository;
import be.nike.projet_sgbd_2025.dal.repositories.CourseRepository;
import be.nike.projet_sgbd_2025.dal.repositories.SiteRepository;
import be.nike.projet_sgbd_2025.dal.repositories.StudentGroupRepository;
import be.nike.projet_sgbd_2025.dal.repositories.TimeSlotRepository;
import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import be.nike.projet_sgbd_2025.dl.entities.Course;
import be.nike.projet_sgbd_2025.dl.entities.Site;
import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  private final CourseRepository courseRepository;
  private final StudentGroupRepository studentGroupRepository;
  private final SiteRepository siteRepository;
  private final ClassroomRepository classroomRepository;
  private final TimeSlotRepository timeSlotRepository;



  @Override
  public void generateWeeklyScheduleForGroup(UUID groupId, LocalDate startDate) {
    StudentGroup studentGroup = studentGroupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
    List<Course> courses = courseRepository.findAll();

    Random random = new Random();

    for (Course course : courses) {
      List<Site> availableSites = siteRepository.findAvailableSitesForCourse(course.getId(), studentGroup.getBaseSite().getUniversity().getId());
      for (Site site : availableSites) {
        List<Classroom> availableClassrooms = classroomRepository.findAvailableClassroomsForSite(site.getId(), studentGroup.getSize());
        for (Classroom classroom : availableClassrooms) {
          for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            LocalTime startTime = LocalTime.of(8, 0).plusMinutes(random.nextInt(13 * 60)); // Heure aléatoire entre 8h et 21h
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime).with(dayOfWeek);
            LocalDateTime endDateTime = startDateTime.plusHours(1);

            if(canScheduleTimeSlot(studentGroup, classroom, startDateTime, endDateTime)) {
              TimeSlot timeSlot = new TimeSlot();
              timeSlot.setId(UUID.randomUUID());
              timeSlot.setCourse(course);
              timeSlot.setClassroom(classroom);
              timeSlot.setSite(site);
              timeSlot.setStartTime(startDateTime);
              timeSlot.setEndTime(endDateTime);

              Set<StudentGroup> studentGroups = new HashSet<>(
                  timeSlot.getGroups() != null ? timeSlot.getGroups() : Set.of());
              studentGroups.add(studentGroup);
              timeSlot.setGroups(studentGroups);

              timeSlotRepository.save(timeSlot);
            }


          }
        }
      }
    }

  }

  @Override
  public void generateWeeklyScheduleForAllGroups(LocalDate startDate) {

    List<StudentGroup> studentGroups = studentGroupRepository.findAll();
    for (StudentGroup studentGroup : studentGroups) {
      generateWeeklyScheduleForGroup(studentGroup.getId(), startDate);
    }

  }

  @Override
  public void generateScheduleForYear(LocalDate startDate, LocalDate endDate) {
    LocalDate currentDate = startDate;
    while (currentDate.isBefore(endDate)) {
      generateWeeklyScheduleForAllGroups(currentDate);
      currentDate = currentDate.plusWeeks(1);
    }

  }

  private boolean canScheduleTimeSlot(StudentGroup studentGroup, Classroom classroom, LocalDateTime startTime, LocalDateTime endTime) {
    // Vérifier l'enchevêtrement des cours pour le groupe
    List<TimeSlot> existingTimeSlots = timeSlotRepository.findByStudentGroupAndTimeRange(studentGroup.getId(), startTime, endTime);
    if (!existingTimeSlots.isEmpty()) {
      return false;
    }

    // Vérifier la disponibilité de la classe
    List<TimeSlot> classroomTimeSlots = timeSlotRepository.findByClassroomAndTimeRange(classroom.getId(), startTime, endTime);
    if (!classroomTimeSlots.isEmpty()) {
      return false;
    }

    // Vérifier les déplacements entre les sites
    List<TimeSlot> previousTimeSlots = timeSlotRepository.findByStudentGroupAndEndTimeBefore(studentGroup.getId(), startTime);
    if (!previousTimeSlots.isEmpty()) {
      TimeSlot previousTimeSlot = previousTimeSlots.get(0);
      if (!previousTimeSlot.getSite().equals(classroom.getSite()) && previousTimeSlot.getEndTime().plusHours(1).isAfter(startTime)) {
        return false;
      }
    }

    return true;
  }





}
