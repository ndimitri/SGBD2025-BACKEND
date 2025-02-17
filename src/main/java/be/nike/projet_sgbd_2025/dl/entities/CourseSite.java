package be.nike.projet_sgbd_2025.dl.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CourseSite extends BaseEntity{

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "site_id", nullable = false)
  private Site site;

  public CourseSite(UUID id, Course course, Site site) {
    super(id);
    this.course = course;
    this.site = site;
  }
}
