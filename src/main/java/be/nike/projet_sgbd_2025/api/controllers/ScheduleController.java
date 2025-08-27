package be.nike.projet_sgbd_2025.api.controllers;

import be.nike.projet_sgbd_2025.bll.services.ScheduleService;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

  private final ScheduleService scheduleService;

  @PostMapping("/generate/week/{groupId}")
  public void generateWeeklyScheduleForGroup(@PathVariable UUID groupId, @RequestParam LocalDate startDate) {
    scheduleService.generateWeeklyScheduleForGroup(groupId, startDate);
  }

  @PostMapping("/generate/all/week")
  public void generateWeeklyScheduleForAllGroups(@RequestParam LocalDate startDate) {
    scheduleService.generateWeeklyScheduleForAllGroups(startDate);
  }

  @PostMapping("/generate/year")
  public void generateScheduleForYear(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam UUID groupId) {
    scheduleService.generateScheduleForYear(groupId, startDate, endDate);
  }



}
