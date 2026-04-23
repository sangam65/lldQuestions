package ElevatorSystem.elevatorStrategy;

import java.util.List;

import ElevatorSystem.entities.Elevator;
import ElevatorSystem.entities.Floor;

public interface ElevatorStrategy {
    Elevator findElevator(List<Elevator>elevators,Floor floor);
}
