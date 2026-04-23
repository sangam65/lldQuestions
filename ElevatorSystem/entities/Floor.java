package ElevatorSystem.entities;

public class Floor {
    private final int floorNumber;
    private final Building building;

    public Building getBuilding() {
        return building;
    }

    public Floor(int floorNumber,Building building) {
        this.floorNumber = floorNumber;
        this.building=building;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

}
