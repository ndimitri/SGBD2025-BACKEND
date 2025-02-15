package be.nike.projet_sgbd_2025.il.initializers;

import be.nike.projet_sgbd_2025.dal.repositories.UniversityRepository;
import be.nike.projet_sgbd_2025.dl.entities.University;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  private final UniversityRepository universityRepository;

  @Override
  public void run(String... args) throws Exception {

    if(universityRepository.count() == 0){
      List<University> universities = List.of(
        new University(UUID.randomUUID(), "UCLouvain"),
        new University(UUID.randomUUID(),"ULB"),
        new University(UUID.randomUUID(),"UNamur")
      );
      universityRepository.saveAll(universities);
    }


  }

}
