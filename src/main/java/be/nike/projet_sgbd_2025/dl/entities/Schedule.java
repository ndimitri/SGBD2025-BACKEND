package be.nike.projet_sgbd_2025.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Schedule extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "group_id", nullable = false)
  private StudentGroup group;

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

  public Schedule(UUID id, Course course, StudentGroup group, Classroom classroom,
      Site site, LocalDateTime startDateTime, LocalDateTime endTime) {
    super(id);
    this.course = course;
    this.group = group;
    this.classroom = classroom;
    this.site = site;
    this.startTime = startDateTime;
    this.endTime = endTime;
  }
}
