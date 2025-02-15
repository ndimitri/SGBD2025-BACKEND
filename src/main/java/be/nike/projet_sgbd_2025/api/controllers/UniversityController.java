package be.nike.projet_sgbd_2025.api.controllers;


import be.nike.projet_sgbd_2025.bll.services.UniversityService;
import be.nike.projet_sgbd_2025.dl.entities.University;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university")
@RequiredArgsConstructor
public class UniversityController {

  private final UniversityService universityService;


  @GetMapping
  public ResponseEntity<List<University>> findAll() {

    List<University> universities = universityService.findAll();

    return ResponseEntity.ok(universities);
  }



}
