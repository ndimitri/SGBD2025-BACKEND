package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public interface ScheduleService {

  public void generateWeeklyScheduleForGroup(UUID groupId, LocalDate startDate);

  public void generateWeeklyScheduleForAllGroups(LocalDate startDate);

  public void generateScheduleForYear(UUID groupId, LocalDate startDate, LocalDate endDate);

  public boolean canScheduleTimeSlot(
      UUID studentGroupId, Classroom classroom, LocalDateTime startTime, LocalDateTime endTime);

}
