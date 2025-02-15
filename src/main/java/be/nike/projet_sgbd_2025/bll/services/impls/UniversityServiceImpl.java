package be.nike.projet_sgbd_2025.bll.services.impls;

import be.nike.projet_sgbd_2025.bll.services.UniversityService;
import be.nike.projet_sgbd_2025.dal.repositories.UniversityRepository;
import be.nike.projet_sgbd_2025.dl.entities.University;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

  private final UniversityRepository universityRepository;

  @Override
  public List<University> findAll() {
    return universityRepository.findAll();
  }

  @Override
  public University findById(UUID id) {
    return universityRepository.findById(id).orElseThrow();
  }

}
