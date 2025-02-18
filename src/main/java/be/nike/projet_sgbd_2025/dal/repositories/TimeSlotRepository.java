package be.nike.projet_sgbd_2025.dal.repositories;

import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {

  List<TimeSlot> findByGroups_Id(UUID groupId);

  @Query("SELECT t FROM TimeSlot t JOIN t.groups sg WHERE sg.id = :groupId AND t.startTime < :endTime AND t.endTime > :startTime")
  List<TimeSlot> findByStudentGroupAndTimeRange(@Param("groupId") UUID groupId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

  @Query("SELECT t FROM TimeSlot t WHERE t.classroom.id = :classroomId AND t.startTime < :endTime AND t.endTime > :startTime")
  List<TimeSlot> findByClassroomAndTimeRange(@Param("classroomId") UUID classroomId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

  @Query("SELECT t FROM TimeSlot t JOIN t.groups sg WHERE sg.id = :groupId AND t.endTime < :startTime ORDER BY t.endTime DESC")
  List<TimeSlot> findByStudentGroupAndEndTimeBefore(@Param("groupId") UUID groupId, @Param("startTime") LocalDateTime startTime);

}
