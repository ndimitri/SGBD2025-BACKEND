package be.nike.projet_sgbd_2025.bll.services.impls;


import be.nike.projet_sgbd_2025.bll.services.ClassroomService;
import be.nike.projet_sgbd_2025.dal.repositories.ClassroomRepository;
import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

   private final ClassroomRepository classroomRepository;

   @Override
   public Optional<Classroom> findById(UUID id) {
     return classroomRepository.findById(id);
   }

  @Override
  public List<Classroom> findAll() {
    return classroomRepository.findAll();
  }


}
