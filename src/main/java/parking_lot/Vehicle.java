package parking_lot;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vehicle {
    private SpaceType type;
    private int plateNum;
    private LocalDateTime parkingStartTime;
}
