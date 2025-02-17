package be.nike.projet_sgbd_2025.dl.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@MappedSuperclass
@Getter
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity {

  @Id
  @Setter
  private UUID id;

  @CreationTimestamp
  private LocalDateTime createAt;

  @UpdateTimestamp
  private LocalDateTime updateAt;

  public BaseEntity(UUID id) {
    this.id = id;
  }

}
