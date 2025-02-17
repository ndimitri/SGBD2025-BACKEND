package be.nike.projet_sgbd_2025.bll.services.impls;

import be.nike.projet_sgbd_2025.bll.services.ScheduleService;
import be.nike.projet_sgbd_2025.dal.repositories.ScheduleRepository;
import be.nike.projet_sgbd_2025.dl.entities.Schedule;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  private final ScheduleRepository scheduleRepository;

  @Override
  public List<Schedule> findAll() {
    return scheduleRepository.findAll();
  }

  @Override
  public Optional<Schedule> findById(UUID id) {
    return scheduleRepository.findById(id);
  }

  @Override
  public Schedule create(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }

  @Override
  public List<Schedule> getScheduleByGroup(UUID groupId) {
    return scheduleRepository.findByGroup_Id(groupId);
  }
}
