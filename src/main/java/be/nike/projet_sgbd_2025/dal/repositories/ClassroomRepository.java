package be.nike.projet_sgbd_2025.dal.repositories;

import be.nike.projet_sgbd_2025.dl.entities.Classroom;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

  @Query("SELECT c FROM Classroom c WHERE c.site.id = :siteId AND c.capacity >= :groupSize")
  List<Classroom> findAvailableClassroomsForSite(@Param("siteId") UUID siteId, @Param("groupSize") int groupSize);



}
