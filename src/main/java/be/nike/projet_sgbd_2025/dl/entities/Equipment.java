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
public class Equipment extends BaseEntity{

  @Column(nullable = false, length = 80)
  private String name;

  @Column(nullable = false)
  private boolean isMobile;

  public Equipment(UUID id, String name, boolean isMobile) {
    super(id);
    this.name = name;
    this.isMobile = isMobile;
  }
}
