package be.nike.projet_sgbd_2025.bll.services;

import be.nike.projet_sgbd_2025.dl.entities.Course;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {

  List<Course> findAll();

  Optional<Course> findById(UUID id);


}
