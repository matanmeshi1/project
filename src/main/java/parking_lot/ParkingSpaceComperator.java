package parking_lot;

import lombok.AllArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
public class ParkingSpaceComperator implements Comparator<ParkingSpace> {

    private final int location;

    @Override
    public int compare(ParkingSpace o1, ParkingSpace o2) {
        int d1 = Math.abs(location - o1.getLocation());
        int d2 = Math.abs(location - o2.getLocation());
        return d1-d2;
    }
}
