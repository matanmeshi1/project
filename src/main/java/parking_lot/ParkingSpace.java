package parking_lot;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingSpace {
    private final int location;
    private final SpaceType spaceType;

}
