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
public class ClassroomEquipment extends BaseEntity{

  @ManyToOne
  @JoinColumn(name = "classroom_id", nullable = false)
  private Classroom classroom;

  @ManyToOne
  @JoinColumn(name = "equipment_id", nullable = false)
  private Equipment equipment;

  public ClassroomEquipment(UUID id, Classroom classroom, Equipment equipment) {
    super(id);
    this.classroom = classroom;
    this.equipment = equipment;
  }
}
