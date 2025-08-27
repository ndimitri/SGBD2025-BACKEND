package be.nike.projet_sgbd_2025.api.forms;

import be.nike.projet_sgbd_2025.dl.entities.StudentGroup;
import be.nike.projet_sgbd_2025.dl.entities.TimeSlot;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
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

    @NotNull(message = "TimeSlot id is required")
    UUID id,

    @NotNull(message = "Classroom id is required")
    UUID classroomId,

    @NotNull(message = "Course id is required")
    UUID courseId,

    @NotNull(message = "At least one student group is required")
    List<UUID> studentGroupsIds

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

