package be.nike.projet_sgbd_2025.api.controllers;

import be.nike.projet_sgbd_2025.bll.services.SiteService;
import be.nike.projet_sgbd_2025.dl.entities.Site;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sites")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SiteController {

  private final SiteService siteService;

  @GetMapping
  public ResponseEntity<List<Site>> findAll() {
    List<Site> sites = siteService.findAll();

    return ResponseEntity.ok(sites);
  }

}
