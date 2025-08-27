package be.nike.projet_sgbd_2025.api.controllers;

import be.nike.projet_sgbd_2025.bll.services.ClassroomService;
import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {


  private final ClassroomService classroomService;

  @GetMapping
  public ResponseEntity<List<Classroom>> findAll() {
    List<Classroom> classrooms = classroomService.findAll();

    return ResponseEntity.ok(classrooms);
  }
}
