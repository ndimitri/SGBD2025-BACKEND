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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    StudentGroup studentGroup = studentGroupRepository.findById(groupId)
        .orElseThrow(() -> new RuntimeException("Group not found"));
    List<Course> courses = courseRepository.findAll();
    Random random = new Random();

    List<LocalTime> roundHours = List.of(
        LocalTime.of(8, 0), LocalTime.of(9, 15), LocalTime.of(10, 30),
        LocalTime.of(11, 45), LocalTime.of(13, 45),
        LocalTime.of(15, 0), LocalTime.of(16, 45),
        LocalTime.of(18, 30), LocalTime.of(19, 45),
        LocalTime.of(21, 0)
    );

    for (Course course : courses) {
      int remainingHours = course.getWeeklyHours(); // Heures à planifier

      while (remainingHours > 0) { // On continue jusqu'à planifier toutes les heures
        List<Site> availableSites = siteRepository.findAvailableSitesForCourse(
            course.getId(), studentGroup.getBaseSite().getUniversity().getId());

        boolean scheduled = false; // Pour s'arrêter dès qu'un créneau est attribué

        for (Site site : availableSites) {
          if (scheduled) break;

          List<Classroom> availableClassrooms = classroomRepository.findAvailableClassroomsForSite(
              site.getId(), studentGroup.getSize());

          for (Classroom classroom : availableClassrooms) {
            if (scheduled) break;

            DayOfWeek dayToAdd = DayOfWeek.of(random.nextInt(6)+1);
            LocalDate selectedDay = startDate.plusDays(dayToAdd.getValue() - startDate.getDayOfWeek().getValue());
            LocalTime startTime = roundHours.get(random.nextInt(roundHours.size()));
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime).with(dayToAdd);
            LocalDateTime endDateTime = startDateTime.plusHours(1);

            System.out.println("Tentative : " + startDateTime + " - " + endDateTime);

            if (canScheduleTimeSlot(studentGroup.getId(), classroom, startDateTime, endDateTime)) {
              System.out.println("Créneau accepté !");
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

              remainingHours--; // On réduit le nombre d'heures à planifier
              System.out.println("Heures restantes pour " + course.getName() + " : " + remainingHours);
              scheduled = true; // Indique qu'un créneau a été trouvé
              break; // On arrête de chercher une salle pour CE créneau
            } else {
              System.out.println("Créneau refusé !");
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

//  @Override
//  public void generateScheduleForYear(LocalDate startDate, LocalDate endDate) {
//    LocalDate currentDate = startDate;
//    while (currentDate.isBefore(endDate)) {
//      generateWeeklyScheduleForAllGroups(currentDate);
//      currentDate = currentDate.plusWeeks(1);
//    }
//
//  }


  @Override
  public void generateScheduleForYear(UUID groupId, LocalDate startDate, LocalDate endDate) {
    // Trouver le premier lundi après (ou le même jour si startDate est déjà un lundi)
    LocalDate firstMonday = startDate.with(DayOfWeek.MONDAY);


    // Générer l'horaire pour la première semaine
//    generateWeeklyScheduleForAllGroups(startDate);
    generateWeeklyScheduleForGroup(groupId, firstMonday);

    // Déterminer la fin de la semaine en cours
    LocalDate endOfFirstWeek = firstMonday.plusDays(6);

    // Récupérer tous les créneaux générés pour cette semaine
//    List<TimeSlot> firstWeekSlots = timeSlotRepository.findByStudentGroupAndTimeRange(groupId,  LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(startDate.plusDays(6), LocalTime.MAX));


    // Récupérer uniquement les créneaux de la première semaine
    List<TimeSlot> firstWeekSlots = timeSlotRepository.findByStudentGroupAndTimeRange(
        groupId,
        LocalDateTime.of(firstMonday, LocalTime.MIN),
        LocalDateTime.of(endOfFirstWeek, LocalTime.MAX)
    );


    LocalDate currentDate = firstMonday.plusWeeks(1);

    // Copier les créneaux sur toutes les semaines suivantes
    while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
      System.out.println("Semaine en cours : " + currentDate);

      List<TimeSlot> newSlots = new ArrayList<>();
      long weeksBetween = ChronoUnit.WEEKS.between(firstMonday, currentDate);

      for (TimeSlot slot : firstWeekSlots) {
        TimeSlot newSlot = new TimeSlot();
        newSlot.setId(UUID.randomUUID());
        newSlot.setCourse(slot.getCourse());
        newSlot.setGroups(slot.getGroups());
        newSlot.setClassroom(slot.getClassroom());
        newSlot.setSite(slot.getSite());

//        // Décaler les dates de la semaine précédente à la semaine en cours
//        newSlot.setStartTime(slot.getStartTime().plusWeeks(ChronoUnit.WEEKS.between(firstMonday, currentDate)));
//        newSlot.setEndTime(slot.getEndTime().plusWeeks(ChronoUnit.WEEKS.between(firstMonday, currentDate)));

        // Décaler simplement les dates
        newSlot.setStartTime(slot.getStartTime().plusWeeks(weeksBetween));
        newSlot.setEndTime(slot.getEndTime().plusWeeks(weeksBetween));

        newSlots.add(newSlot);

      }
      timeSlotRepository.saveAll(newSlots);
      currentDate = currentDate.plusWeeks(1);
    }
  }


  @Override
  public boolean canScheduleTimeSlot(UUID studentGroupId, Classroom classroom, LocalDateTime startTime, LocalDateTime endTime) {
    // Vérifier l'enchevêtrement des cours pour le groupe
    List<TimeSlot> existingTimeSlots = timeSlotRepository.findByStudentGroupAndTimeRange(studentGroupId, startTime, endTime);
    if (!existingTimeSlots.isEmpty()) {
      return false;
    }

    // Vérifier la disponibilité de la classe
    List<TimeSlot> classroomTimeSlots = timeSlotRepository.findByClassroomAndTimeRange(classroom.getId(), startTime, endTime);
    if (!classroomTimeSlots.isEmpty()) {
      return false;
    }

    // Vérifier les déplacements entre les sites
    List<TimeSlot> previousTimeSlots = timeSlotRepository.findByStudentGroupAndEndTimeBefore(studentGroupId, startTime);


    // Si des créneaux précédents existent, vérifier le déplacement entre les sites
    // Récupérer le dernier créneau du groupe avant la date de début
    TimeSlot lastTimeSlot = previousTimeSlots.isEmpty() ? null : previousTimeSlots.get(0);

    if (lastTimeSlot != null && !lastTimeSlot.getSite().getName().equals(classroom.getSite().getName())) {
      // Vérifier si le créneau précédent permet une pause de 1h pour le déplacement
      if (lastTimeSlot.getEndTime().plusHours(1).isAfter(startTime)) {
        return false; // Pas assez de temps pour le déplacement
      }
    }


    List<TimeSlot> nextTimeSlots = timeSlotRepository.findNextTimeSlot(studentGroupId, startTime);


    // Vérifier aussi le prochain créneau après celui qu'on veut placer
    TimeSlot nextTimeSlot = nextTimeSlots.isEmpty() ? null : nextTimeSlots.get(0);
    if (nextTimeSlot != null && !nextTimeSlot.getSite().equals(classroom.getSite())) {
      if (startTime.plusHours(2).isAfter(nextTimeSlot.getStartTime())) {
        return false; // Pas assez de temps pour le déplacement vers le prochain créneau
      }
    }

    return true;
  }





}
