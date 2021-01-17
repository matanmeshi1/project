package parking_lot;


import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;


public class Gate {
    private final int location;
    private Map<SpaceType, PriorityQueue<ParkingSpace>> freeSpaces;

    public Gate(int location) {
        this.location = location;
        freeSpaces = new HashMap<>();
        Stream.of(SpaceType.values()).forEach(type -> {
                freeSpaces.put(type, new PriorityQueue<>(new ParkingSpaceComperator(location)));
    });
        //TODO - define comparator
    }

    public ParkingSpace assignSpace(SpaceType spaceType) {
        PriorityQueue<ParkingSpace> freeTypeSpace = freeSpaces.get(spaceType);
        if (freeTypeSpace == null)
            return null;

        return freeTypeSpace.poll();

    }

    public boolean freeSpace(ParkingSpace space) {

        SpaceType spaceType = space.getSpaceType();
        PriorityQueue<ParkingSpace> freeTypeSpace = freeSpaces.get(spaceType);
        if (freeTypeSpace == null)
            return false;
        freeTypeSpace.add(space);
        return true;
    }

}
