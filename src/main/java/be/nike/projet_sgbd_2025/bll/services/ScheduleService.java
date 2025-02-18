package be.nike.projet_sgbd_2025.bll.services;

import java.time.LocalDate;
import java.util.UUID;


public interface ScheduleService {

  public void generateWeeklyScheduleForGroup(UUID groupId, LocalDate startDate);

  public void generateWeeklyScheduleForAllGroups(LocalDate startDate);

  public void generateScheduleForYear(LocalDate startDate, LocalDate endDate);

}
