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
    University u1 = University.builder().name("ULB").id(UUID.randomUUID()).build();
    University u2 = University.builder().name("UNamur").id(UUID.randomUUID()).build();
    universityRepository.saveAll(Arrays.asList(u1, u2));

    // Initialiser les sites
    Site s1 = Site.builder().name("Solbosh").university(u1).id(UUID.randomUUID()).build();
    Site s2 = Site.builder().name("La Plaine").university(u1).id(UUID.randomUUID()).build();
    Site s3 = Site.builder().name("LLN").university(u2).id(UUID.randomUUID()).build();
    Site s4 = Site.builder().name("Alma").university(u2).id(UUID.randomUUID()).build();
    siteRepository.saveAll(Arrays.asList(s1, s2, s3, s4));

    // Initialiser les salles de classe
    Classroom c1 = Classroom.builder().name("Salle 101").matricule("MAT101").site(s1).capacity(50).id(UUID.randomUUID()).build();
    Classroom c2 = Classroom.builder().name("Salle 202").matricule("MAT202").site(s1).capacity(40).id(UUID.randomUUID()).build();
    Classroom c3 = Classroom.builder().name("Salle 303").matricule("MAT303").site(s2).capacity(100).id(UUID.randomUUID()).build();
    Classroom c4 = Classroom.builder().name("Salle 404").matricule("MAT404").site(s2).capacity(40).id(UUID.randomUUID()).build();
    Classroom c5 = Classroom.builder().name("Salle 505").matricule("MAT505").site(s3).capacity(30).id(UUID.randomUUID()).build();
    Classroom c6 = Classroom.builder().name("Salle 606").matricule("MAT606").site(s3).capacity(40).id(UUID.randomUUID()).build();
    Classroom c7 = Classroom.builder().name("Salle 707").matricule("MAT707").site(s4).capacity(50).id(UUID.randomUUID()).build();
    Classroom c8 = Classroom.builder().name("Salle 808").matricule("MAT808").site(s4).capacity(40).id(UUID.randomUUID()).build();
    classroomRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));

    // Initialiser les groupes d'étudiants
    StudentGroup g1 = StudentGroup.builder().name("Groupe A").size(30).baseSite(s1).id(UUID.randomUUID()).build();
    StudentGroup g2 = StudentGroup.builder().name("Groupe B").size(25).baseSite(s2).id(UUID.randomUUID()).build();
    StudentGroup g3 = StudentGroup.builder().name("Groupe C").size(20).baseSite(s3).id(UUID.randomUUID()).build();
    StudentGroup g4 = StudentGroup.builder().name("Groupe D").size(25).baseSite(s4).id(UUID.randomUUID()).build();
    studentGroupRepository.saveAll(Arrays.asList(g1, g2, g3, g4));

    // Initialiser les cours
    Course course1 = Course.builder().name("Mathématiques").professor("P.Koelberg").weeklyHours(4).id(UUID.randomUUID()).build();
    Course course2 = Course.builder().name("Informatique").professor("M.Lipohuola").weeklyHours(5).id(UUID.randomUUID()).build();
    Course course3 = Course.builder().name("Histoire").professor("F.Duromas").weeklyHours(3).id(UUID.randomUUID()).build();
    Course course4 = Course.builder().name("Biologie").professor("T.Pochet").weeklyHours(3).id(UUID.randomUUID()).build();
    Course course5 = Course.builder().name("Politique").professor("L.Crizwich").weeklyHours(2).id(UUID.randomUUID()).build();
    Course course6 = Course.builder().name("Anglais").professor("S.Brano").weeklyHours(4).id(UUID.randomUUID()).build();
    Course course7 = Course.builder().name("Physique").professor("N.Colas").weeklyHours(3).id(UUID.randomUUID()).build();
    Course course8 = Course.builder().name("Chimie").professor("K.Smet").weeklyHours(3).id(UUID.randomUUID()).build();
    courseRepository.saveAll(Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8));

    // Initialiser les relations cours-site
    CourseSite cs1 = CourseSite.builder().course(course1).site(s1).id(UUID.randomUUID()).build();
    CourseSite cs2 = CourseSite.builder().course(course2).site(s1).id(UUID.randomUUID()).build();
    CourseSite cs3 = CourseSite.builder().course(course3).site(s1).id(UUID.randomUUID()).build();
    CourseSite cs4 = CourseSite.builder().course(course4).site(s1).id(UUID.randomUUID()).build();
    CourseSite cs5 = CourseSite.builder().course(course5).site(s2).id(UUID.randomUUID()).build();
    CourseSite cs6 = CourseSite.builder().course(course6).site(s2).id(UUID.randomUUID()).build();
    CourseSite cs7 = CourseSite.builder().course(course7).site(s2).id(UUID.randomUUID()).build();
    CourseSite cs8 = CourseSite.builder().course(course8).site(s2).id(UUID.randomUUID()).build();
    courseSiteRepository.saveAll(Arrays.asList(cs1, cs2, cs3, cs4, cs5, cs6, cs7, cs8));

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
