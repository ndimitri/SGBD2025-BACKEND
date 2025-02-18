package be.nike.projet_sgbd_2025.il.initializers;

import be.nike.projet_sgbd_2025.dal.repositories.ClassroomEquipmentRepository;
import be.nike.projet_sgbd_2025.dal.repositories.ClassroomRepository;
import be.nike.projet_sgbd_2025.dal.repositories.CourseRepository;
import be.nike.projet_sgbd_2025.dal.repositories.CourseSiteRepository;
import be.nike.projet_sgbd_2025.dal.repositories.EquipmentRepository;
import be.nike.projet_sgbd_2025.dal.repositories.TimeSlotRepository;
import be.nike.projet_sgbd_2025.dal.repositories.SiteRepository;
import be.nike.projet_sgbd_2025.dal.repositories.StudentGroupRepository;
import be.nike.projet_sgbd_2025.dal.repositories.UniversityRepository;
import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import be.nike.projet_sgbd_2025.dl.entities.ClassroomEquipment;
import be.nike.projet_sgbd_2025.dl.entities.Course;
import be.nike.projet_sgbd_2025.dl.entities.CourseSite;
import be.nike.projet_sgbd_2025.dl.entities.Equipment;
import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import be.nike.projet_sgbd_2025.dl.entities.Site;
import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import be.nike.projet_sgbd_2025.dl.entities.University;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  //Repositories
  private final UniversityRepository universityRepository;
  private final SiteRepository siteRepository;
  private final ClassroomRepository classroomRepository;
  private final StudentGroupRepository studentGroupRepository;
  private final CourseRepository courseRepository;
  private final CourseSiteRepository courseSiteRepository;
  private final EquipmentRepository equipmentRepository;
  private final ClassroomEquipmentRepository classroomEquipmentRepository;
  private final TimeSlotRepository timeSlotRepository;


  @Override
  public void run(String... args) throws Exception {

// Initialiser les universités
    University u1 = University.builder().name("Université de Paris").id(UUID.randomUUID()).build();
    University u2 = University.builder().name("Université de Lyon").id(UUID.randomUUID()).build();
    universityRepository.saveAll(Arrays.asList(u1, u2));

    // Initialiser les sites
    Site s1 = Site.builder().name("Site Paris Centre").university(u1).id(UUID.randomUUID()).build();
    Site s2 = Site.builder().name("Site Lyon Est").university(u2).id(UUID.randomUUID()).build();
    siteRepository.saveAll(Arrays.asList(s1, s2));

    // Initialiser les salles de classe
    Classroom c1 = Classroom.builder().name("Salle 101").matricule("MAT101").site(s1).capacity(50).id(UUID.randomUUID()).build();
    Classroom c2 = Classroom.builder().name("Salle 202").matricule("MAT202").site(s2).capacity(40).id(UUID.randomUUID()).build();
    classroomRepository.saveAll(Arrays.asList(c1, c2));

    // Initialiser les groupes d'étudiants
    StudentGroup g1 = StudentGroup.builder().name("Groupe A").size(30).baseSite(s1).id(UUID.randomUUID()).build();
    StudentGroup g2 = StudentGroup.builder().name("Groupe B").size(25).baseSite(s2).id(UUID.randomUUID()).build();
    studentGroupRepository.saveAll(Arrays.asList(g1, g2));

    // Initialiser les cours
    Course course1 = Course.builder().name("Mathématiques").professor("P.Koelberg").id(UUID.randomUUID()).build();
    Course course2 = Course.builder().name("Informatique").professor("M.Lipohuola").id(UUID.randomUUID()).build();
    courseRepository.saveAll(Arrays.asList(course1, course2));

    // Initialiser les relations cours-site
    CourseSite cs1 = CourseSite.builder().course(course1).site(s1).id(UUID.randomUUID()).build();
    CourseSite cs2 = CourseSite.builder().course(course2).site(s2).id(UUID.randomUUID()).build();
    courseSiteRepository.saveAll(Arrays.asList(cs1, cs2));

    // Initialiser le matériel audiovisuel
    Equipment eq1 = Equipment.builder().name("Projecteur").isMobile(true).id(UUID.randomUUID()).build();
    Equipment eq2 = Equipment.builder().name("Tableau Blanc").isMobile(false).id(UUID.randomUUID()).build();
    equipmentRepository.saveAll(Arrays.asList(eq1, eq2));

    // Initialiser les relations classe-matériel
    ClassroomEquipment ce1 = ClassroomEquipment.builder().classroom(c1).equipment(eq1).id(UUID.randomUUID()).build();
    ClassroomEquipment ce2 = ClassroomEquipment.builder().classroom(c2).equipment(eq2).id(UUID.randomUUID()).build();
    classroomEquipmentRepository.saveAll(Arrays.asList(ce1, ce2));



  }

}
