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
public class StudentGroup extends BaseEntity{

  @Column(nullable = false, unique = true, length = 50)
  private String name;

  @Column(nullable = false)
  private int size;

  @ManyToOne
  @JoinColumn(name= "base_site_id", nullable = false)
  private Site baseSite;

  public StudentGroup(UUID id, String name, int size, Site baseSite) {
    super(id);
    this.name = name;
    this.size = size;
    this.baseSite = baseSite;
  }
}
