package be.nike.projet_sgbd_2025.api.controllers;

import be.nike.projet_sgbd_2025.bll.services.ScheduleService;
import be.nike.projet_sgbd_2025.dl.entities.Schedule;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ScheduleController {

  private final ScheduleService scheduleService;

  @GetMapping
  public ResponseEntity<List<Schedule>> findAll() {
    List<Schedule> schedules = scheduleService.findAll();

    return ResponseEntity.ok(schedules);
  }


  @GetMapping("/{id}")
  public ResponseEntity<Schedule> findById(UUID id) {
    Optional<Schedule> schedule = scheduleService.findById(id);

    return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Schedule> create(@RequestBody Schedule schedule) {
    Schedule createdSchedule = scheduleService.create(schedule);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
  }

  @GetMapping("/group/{groupId}")
  public List<Schedule> getScheduleByGroup(@PathVariable UUID groupId) {
    return scheduleService.getScheduleByGroup(groupId);
  }


}
