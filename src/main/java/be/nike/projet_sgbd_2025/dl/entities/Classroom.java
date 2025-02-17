package be.nike.projet_sgbd_2025.dl.entities;


import jakarta.persistence.Column;
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
public class Classroom extends BaseEntity{

  @Column(nullable = false, unique = true, length = 50)
  private String name;

  @Column(nullable = false, unique = true, length = 20)
  private String matricule;

  @ManyToOne
  @JoinColumn(name = "site_id", nullable = false)
  private Site site;

  @Column(nullable = false)
  private int capacity;

  public Classroom(UUID id, String name, String matricule, Site site, int capacity) {
    super(id);
    this.name = name;
    this.matricule = matricule;
    this.site = site;
    this.capacity = capacity;
  }
}
