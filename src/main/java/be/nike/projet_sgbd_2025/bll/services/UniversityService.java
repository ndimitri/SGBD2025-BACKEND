package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.University;
import java.util.List;
import java.util.UUID;

public interface UniversityService {

  List<University> findAll();

  University findById(UUID id);

}
