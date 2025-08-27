package be.nike.projet_sgbd_2025.bll.services.impls;

import be.nike.projet_sgbd_2025.bll.services.CourseService;
import be.nike.projet_sgbd_2025.dal.repositories.CourseRepository;
import be.nike.projet_sgbd_2025.dl.entities.Course;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;


  @Override
  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  @Override
  public Optional<Course> findById(UUID id) {
    return courseRepository.findById(id);
  }
}
