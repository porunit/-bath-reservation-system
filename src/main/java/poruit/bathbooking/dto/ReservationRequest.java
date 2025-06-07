package poruit.bathbooking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    private String tgUsername;
    private Long bathhouseId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
