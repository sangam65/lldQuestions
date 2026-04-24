package ElevatorSystem.entities;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import ElevatorSystem.enums.Direction;

public class Elevator {
    private Floor currentFloor;

    private final char elevatorId;

    public char getElevatorId() {
        return elevatorId;
    }

    private Direction direction;
    private PriorityQueue<Floor> floors;
    private Queue<Floor> pendingFloors;

    public Floor getCurrentFloor() {
        return currentFloor;
    }

   

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Elevator(char elevatorId, Floor currentFloor) {
        this.elevatorId = elevatorId;
        this.floors = new PriorityQueue<>((Floor a, Floor b) -> a.getFloorNumber() - b.getFloorNumber());
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.pendingFloors = new LinkedList<>();
    }

    public void addFloor(Floor currentFloor, Floor destFloor, Direction direction) {
        if (this.direction.equals(Direction.IDLE)) {
            this.direction = direction;
            changeFloorOrdering();
            this.floors.add(currentFloor);
            this.floors.add(destFloor);
        } else if (this.direction != direction) {
            this.pendingFloors.add(destFloor);
            this.pendingFloors.add(currentFloor);
        } else {
            this.floors.add(currentFloor);
            this.floors.add(destFloor);
        }

    }

    // will be scheduled or async
    // @Scheduled
    public void step() {
        if (floors.isEmpty()) {
            if (pendingFloors.isEmpty()){
                this.direction=Direction.IDLE;
                 return;
            }
               
            this.direction = (this.direction == Direction.DOWN) ? Direction.UP : Direction.DOWN;
            changeFloorOrdering();
            while (!pendingFloors.isEmpty()) {
                this.floors.add(pendingFloors.poll());
            }

        }

        this.currentFloor = this.floors.poll();

        this.direction = floors.isEmpty()
                ? Direction.IDLE
                : (floors.peek().getFloorNumber() > currentFloor.getFloorNumber()
                        ? Direction.UP
                        : Direction.DOWN);

        System.out.println("Elevator " + elevatorId +
                " at floor " + currentFloor.getFloorNumber() +
                " direction: " + direction);
    }

    private void changeFloorOrdering() {
        this.floors = new PriorityQueue<>(
                !direction.equals(Direction.UP)
                        ? (a, b) -> b.getFloorNumber() - a.getFloorNumber()
                       : (a, b) -> a.getFloorNumber() - b.getFloorNumber());
                        // : (a, b) -> b.getFloorNumber() - a.getFloorNumber());
    }

}
