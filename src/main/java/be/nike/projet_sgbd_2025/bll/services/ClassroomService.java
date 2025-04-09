package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import java.util.Optional;
import java.util.UUID;

public interface ClassroomService {

  Optional<Classroom> findById(UUID id);

}
