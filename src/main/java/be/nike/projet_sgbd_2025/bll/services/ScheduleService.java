package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.Schedule;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleService {
  List<Schedule> findAll();

  Optional<Schedule> findById(UUID id);

  Schedule create(Schedule schedule);

  List<Schedule> getScheduleByGroup(UUID groupId);

}
