package be.nike.projet_sgbd_2025.dl.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Site extends BaseEntity{

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "university_id")
  private University university;

  public Site(UUID id, String name, University university) {
    super(id);
    this.name = name;
    this.university = university;
  }
}
