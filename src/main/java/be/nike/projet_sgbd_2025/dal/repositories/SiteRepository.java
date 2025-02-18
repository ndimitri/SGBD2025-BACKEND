package be.nike.projet_sgbd_2025.dal.repositories;

import be.nike.projet_sgbd_2025.dl.entities.Site;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, UUID> {


  @Query("SELECT s FROM Site s JOIN CourseSite cs ON s.id = cs.site.id WHERE cs.course.id = :courseId AND s.university.id = :universityId")
  List<Site> findAvailableSitesForCourse(@Param("courseId") UUID courseId, @Param("universityId") UUID universityId);

}
