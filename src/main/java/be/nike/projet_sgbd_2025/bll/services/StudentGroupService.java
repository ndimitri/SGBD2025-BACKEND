package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentGroupService {

  List<StudentGroup> findAll();

  Optional<StudentGroup> findById(UUID id);

}
