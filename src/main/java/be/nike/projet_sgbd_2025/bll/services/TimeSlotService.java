package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimeSlotService {
  List<TimeSlot> findAll();

  Optional<TimeSlot> findById(UUID id);

  TimeSlot create(TimeSlot timeSlot);

  List<TimeSlot> getScheduleByGroup(UUID groupId);

  void update(TimeSlot timeSlot);

}
