package parking_lot;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Getter
public class ParkingLot {
    private final Map<SpaceType, Integer> totalSpaces;
    private final float hourlyRate;
    private Map<SpaceType,Integer> takenSpaces;
    private List<Gate> gates;
    private Map<Vehicle, ParkingSpace> vehicleParkingSpaceMap;

    public ParkingLot(List<Gate> gates, List<ParkingSpace> spaces, float hourlyRate) {
        this.gates = gates;
        this.hourlyRate = hourlyRate;
        totalSpaces = new HashMap<>();
        takenSpaces = new HashMap<>();
        vehicleParkingSpaceMap = new HashMap<>();

        Stream.of(SpaceType.values()).forEach(
                spaceType -> {
                    takenSpaces.put(spaceType, 0);
                    totalSpaces.put(spaceType,0);
                }
        );

        for(ParkingSpace space : spaces) {
            gates.forEach(gate -> gate.freeSpace(space));
            int currSpace = totalSpaces.get(space.getSpaceType());
            totalSpaces.put(space.getSpaceType(), currSpace+1);
            takenSpaces.put(space.getSpaceType(), currSpace+1);
        }
    }

    public boolean Pay(Payment p, Vehicle v) {
        float totalPrice = hourlyRate* Duration.between(v.getParkingStartTime(), LocalDateTime.now()).toHours();

        boolean isPayed = p.pay(totalPrice);
        if(isPayed) {
            freeSpace(v);
        }
        return isPayed;
    }

    private synchronized  void  freeSpace(Vehicle v) {
        ParkingSpace space = vehicleParkingSpaceMap.get(v);
        gates.forEach(gate -> gate.freeSpace(space));
        int currSpaceCount = totalSpaces.get(space.getSpaceType());
        takenSpaces.put(space.getSpaceType(), currSpaceCount-1);
        vehicleParkingSpaceMap.put(v, null);
    }

    public synchronized void takeSpace(Vehicle v, Gate g) {
        SpaceType type = v.getType();
        ParkingSpace space =g.assignSpace(type);
        int currSpaceCount = totalSpaces.get(space.getSpaceType());
        takenSpaces.put(space.getSpaceType(), currSpaceCount+1);
        vehicleParkingSpaceMap.put(v, space);
    }

}
