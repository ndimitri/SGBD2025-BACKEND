package be.nike.projet_sgbd_2025.bll.services.impls;

import be.nike.projet_sgbd_2025.bll.services.StudentGroupService;
import be.nike.projet_sgbd_2025.dal.repositories.StudentGroupRepository;
import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentGroupServiceImpl implements StudentGroupService {

  private final StudentGroupRepository studentGroupRepository;


  @Override
  public List<StudentGroup> findAll() {
    return studentGroupRepository.findAll();
  }

  @Override
  public Optional<StudentGroup> findById(UUID id) {
    return studentGroupRepository.findById(id);
  }
}
