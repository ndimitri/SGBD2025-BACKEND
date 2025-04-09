package be.nike.projet_sgbd_2025.api.controllers;

import be.nike.projet_sgbd_2025.api.forms.TimeSlotForm;
import be.nike.projet_sgbd_2025.bll.services.ClassroomService;
import be.nike.projet_sgbd_2025.bll.services.ScheduleService;
import be.nike.projet_sgbd_2025.bll.services.StudentGroupService;
import be.nike.projet_sgbd_2025.bll.services.TimeSlotService;
import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import be.nike.projet_sgbd_2025.dl.entities.Site;
import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timeslots")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TimeSlotController {

  private final TimeSlotService timeSlotService;
  private final ScheduleService scheduleService;
  private final ClassroomService classroomService;
  private final StudentGroupService studentGroupService;

  @GetMapping
  public ResponseEntity<List<TimeSlot>> findAll() {
    List<TimeSlot> timeSlots = timeSlotService.findAll();

    return ResponseEntity.ok(timeSlots);
  }


  @GetMapping("/{id}")
  public ResponseEntity<TimeSlot> findById(@PathVariable UUID id) {
    Optional<TimeSlot> schedule = timeSlotService.findById(id);

    return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }


  @PostMapping
  public ResponseEntity<TimeSlot> create(@RequestBody TimeSlot timeSlot) {
    TimeSlot createdTimeSlot = timeSlotService.create(timeSlot);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTimeSlot);
  }

  @GetMapping("/group/{groupId}")
  public List<TimeSlot> getScheduleByGroup(@PathVariable UUID groupId) {
    return timeSlotService.getScheduleByGroup(groupId);
  }


  @PutMapping("")
  public ResponseEntity<?> updateTimeSlot( @Valid @RequestBody TimeSlotForm updatedSlot, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      List<String> errors = bindingResult.getAllErrors().stream().map(
          DefaultMessageSourceResolvable::getDefaultMessage).toList();
    }

    TimeSlot timeSlot = updatedSlot.toTimeSlot();
    StudentGroup groupToAdd = studentGroupService.findById(updatedSlot.id()).orElseThrow();
    timeSlot.setGroups(Set.of(groupToAdd));

    Classroom classroomToAdd = classroomService.findById(updatedSlot.classroomId()).orElseThrow();
    timeSlot.setClassroom(classroomToAdd);

    Site siteToAdd = classroomToAdd.getSite();
    timeSlot.setSite(siteToAdd);

    timeSlotService.update(timeSlot);

    return ResponseEntity.noContent().build();

  }


}
