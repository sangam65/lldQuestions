package ElevatorSystem.elevatorStrategy;

import java.util.List;

import ElevatorSystem.entities.Elevator;
import ElevatorSystem.entities.Floor;
import ElevatorSystem.enums.Direction;

public class LookUpElevatorStrategy implements ElevatorStrategy{

    @Override
    public Elevator findElevator(List<Elevator> elevators, Floor floor) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        
        // First, prefer elevators that are above the floor and not going up (idle or down)
        for (Elevator elevator : elevators) {
            if (elevator.getCurrentFloor().getFloorNumber() > floor.getFloorNumber()) {
                if (elevator.getDirection().equals(Direction.UP)) {
                    int distance = elevator.getCurrentFloor().getFloorNumber() - floor.getFloorNumber();
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }
        
        // If no suitable elevator found above, consider all elevators and find the closest
        if (bestElevator == null) {
            minDistance = Integer.MAX_VALUE;
            for (Elevator elevator : elevators) {
                int distance = Math.abs(elevator.getCurrentFloor().getFloorNumber() - floor.getFloorNumber());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }
        
        return bestElevator;
    }

}
