package be.nike.projet_sgbd_2025.bll.services.impls;

import be.nike.projet_sgbd_2025.bll.services.TimeSlotService;
import be.nike.projet_sgbd_2025.dal.repositories.TimeSlotRepository;
import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

  private final TimeSlotRepository timeSlotRepository;

  @Override
  public List<TimeSlot> findAll() {
    return timeSlotRepository.findAll();
  }

  @Override
  public Optional<TimeSlot> findById(UUID id) {
    return timeSlotRepository.findById(id);
  }

  @Override
  public TimeSlot create(TimeSlot timeSlot) {
    return timeSlotRepository.save(timeSlot);
  }

  @Override
  public List<TimeSlot> getScheduleByGroup(UUID groupId) {
    return timeSlotRepository.findByGroups_Id(groupId);
  }

  @Override
  public void update(TimeSlot timeSlot) {

    TimeSlot existingTimeSlot = timeSlotRepository.findById(timeSlot.getId()).orElseThrow(() -> new RuntimeException("TimeSlot not found"));

    if(existingTimeSlot != null) {
      existingTimeSlot.setStartTime(timeSlot.getStartTime());
      existingTimeSlot.setEndTime(timeSlot.getEndTime());
      existingTimeSlot.setClassroom(timeSlot.getClassroom());

      existingTimeSlot.getCourse().setProfessor(timeSlot.getCourse().getProfessor());
      existingTimeSlot.setCourse(timeSlot.getCourse());
      existingTimeSlot.setSite(timeSlot.getSite());
      existingTimeSlot.setGroups(timeSlot.getGroups());

      timeSlotRepository.save(existingTimeSlot);
    } else {
      throw new RuntimeException("TimeSlot not found");
    }

  }
}
