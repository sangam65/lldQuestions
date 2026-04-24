package ElevatorSystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import ElevatorSystem.enums.Direction;

public class Elevator {
    private Floor currentFloor;
    private Floor nextFloor;
    private final char elevatorId;

    public char getElevatorId() {
        return elevatorId;
    }

    private Direction direction;
    private PriorityQueue<Floor> floors;

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public Floor getNextFloor() {
        return nextFloor;
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
        this.nextFloor = null;
        this.direction = Direction.IDLE;
    }

    public void addFloor(Floor currentFloor, Floor destFloor, Direction direction) {
        if (this.direction.equals(Direction.IDLE)) {
            this.direction = direction;
            this.floors = new PriorityQueue<>(
                    direction.equals(Direction.UP)
                            ? (a, b) -> a.getFloorNumber() - b.getFloorNumber()
                            : (a, b) -> b.getFloorNumber() - a.getFloorNumber());
        }
        this.floors.add(currentFloor);
        this.floors.add(destFloor);

    }

    // will be scheduled or async
    // @Scheduled
    public void step() {
        if (floors.isEmpty())
            return;

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

}
