package be.nike.projet_sgbd_2025.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
public class TimeSlot extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToMany
  @JoinTable(
      name = "time_slot_student_group",
      joinColumns = @JoinColumn(name = "time_slot_id"),
      inverseJoinColumns = @JoinColumn(name = "student_group_id"))
  private Set<StudentGroup> groups = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "classroom_id", nullable = false)
  private Classroom classroom;

  @ManyToOne
  @JoinColumn(name = "site_id", nullable = false)
  private Site site;

  @Column(nullable = false)
  private LocalDateTime startTime;

  @Column(nullable = false)
  private LocalDateTime endTime;

  public TimeSlot(UUID id, Course course, Set<StudentGroup> groups, Classroom classroom,
      Site site, LocalDateTime startDateTime, LocalDateTime endTime) {
    super(id);
    this.course = course;
    this.groups = groups;
    this.classroom = classroom;
    this.site = site;
    this.startTime = startDateTime;
    this.endTime = endTime;
  }

  public TimeSlot(UUID id, LocalDateTime startTime, LocalDateTime endTime) {
    super(id);
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
