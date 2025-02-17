package be.nike.projet_sgbd_2025.dl.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class University extends BaseEntity {

  @Column(nullable = false, unique = true, length = 100)
  private String name;

  public University(UUID id, String name) {
    super(id);
    this.name = name;
  }

}
