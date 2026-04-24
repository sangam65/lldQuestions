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
    public void addFloor(Floor currentFloor,Floor destFloor,Direction direction){
        if(nextFloor==null){
            this.nextFloor=currentFloor;
            this.direction=direction;
            if(this.direction.equals(Direction.UP)){
                 this.floors = new PriorityQueue<>((a,b) -> a.getFloorNumber() - b.getFloorNumber()); // min-heap ✅

            }
            else{
                 this.floors = new PriorityQueue<>((a,b) -> b.getFloorNumber() - a.getFloorNumber()); // max-heap ✅

            }
        }
        this.floors.add(currentFloor);
        this.floors.add(destFloor);
        moveElevator();
    }
    // will be scheduled or async
    private void moveElevator(){
        this.currentFloor=this.nextFloor;
        this.floors.remove();
        if(this.floors.isEmpty()){
           this.nextFloor=null; 
           
           this.direction=Direction.IDLE;
        }
        else{
            this.nextFloor=this.floors.poll();


        }

    }
    


}
