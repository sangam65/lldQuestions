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
    public Elevator(char elevatorId,Floor currentFloor) {
        this.elevatorId = elevatorId;
        this.floors=new PriorityQueue<>((Floor a,Floor b)->a.getFloorNumber()-b.getFloorNumber());
        this.currentFloor=currentFloor;
        this.nextFloor=null;
        this.direction=Direction.IDLE;
    }
    public void addFloor(Floor currentFloor,Floor destFloor){
        if(nextFloor==null){
            this.nextFloor=currentFloor;
        }
        this.floors.add(currentFloor);
        this.floors.add(destFloor);
        moveElevator();
    }
    private void moveElevator(){
        this.currentFloor=this.nextFloor;
        this.floors.remove();
        if(this.floors.isEmpty()){
           this.nextFloor=null; 
           if(this.direction.equals(Direction.UP)){
                this.direction=Direction.DOWN;
                this.floors=new PriorityQueue<>((Floor a,Floor b)->b.getFloorNumber()-a.getFloorNumber());

           }
           else{
             this.direction=Direction.UP;
                this.floors=new PriorityQueue<>((Floor a,Floor b)->a.getFloorNumber()-b.getFloorNumber());
           }
        }
        else{
            this.nextFloor=this.floors.poll();


        }

    }
    


}


class FloorDir{
    private Floor floor;
    private Direction direction;
    public FloorDir(Floor floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }
    public Floor getFloor() {
        return floor;
    }
    public void setFloor(Floor floor) {
        this.floor = floor;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}