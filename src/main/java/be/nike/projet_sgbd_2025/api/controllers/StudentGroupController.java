package be.nike.projet_sgbd_2025.api.controllers;


import be.nike.projet_sgbd_2025.bll.services.StudentGroupService;
import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentGroupController {

  private final StudentGroupService studentGroupService;

  @GetMapping
  public ResponseEntity<List<StudentGroup>> findAll() {
    List<StudentGroup> studentGroups = studentGroupService.findAll();

    return ResponseEntity.ok(studentGroups);
  }



}
