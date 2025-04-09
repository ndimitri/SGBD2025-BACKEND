package be.nike.projet_sgbd_2025.api.forms;

import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public record TimeSlotForm(
    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time must be in the future or present")
    LocalDateTime startTime,

    @NotNull(message = "End time is required")
    @FutureOrPresent(message = "End time must be in the future or present")
    LocalDateTime endTime,

    UUID id,

    String professor,

    UUID classroomId,

    UUID courseId,

    UUID siteId,

    UUID studentGroupId

) {

  // Convertit le DTO en entité TimeSlot
  public TimeSlot toTimeSlot() {
    return new TimeSlot(
        id, // on passe l'ID existant du TimeSlot
        this.startTime,
        this.endTime
    );
  }
}

