package ElevatorSystem.elevatorStrategy;

import java.util.List;

import ElevatorSystem.entities.Elevator;
import ElevatorSystem.entities.Floor;
import ElevatorSystem.enums.Direction;

public interface ElevatorStrategy {
    Elevator findElevator(List<Elevator>elevators,Floor curentFloor,Floor destinationFloor,Direction direction);
}
