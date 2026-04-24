package ElevatorSystem.entities;

import java.util.ArrayList;
import java.util.List;

import ElevatorSystem.enums.Direction;

public class Elevator {
    private Floor currentFloor;
    private Floor nextFloor;
    private final char elevatorId;
    public char getElevatorId() {
        return elevatorId;
    }
    private Direction direction;
    private List<Floor> floors;
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
        this.floors=new ArrayList<>();
        this.currentFloor=currentFloor;
        this.nextFloor=null;
        this.direction=Direction.IDLE;
    }
    public void addFloor(Floor floor){
        if(nextFloor==null){
            this.nextFloor=floor;
        }
        this.floors.add(floor);
        moveElevator();
    }
    private void moveElevator(){
        this.currentFloor=this.nextFloor;
        this.floors.remove(0);
        if(this.floors.isEmpty()){
           this.nextFloor=null; 
           this.direction=Direction.IDLE;
        }
        else{
            this.nextFloor=this.floors.get(0);

        }

    }
    


}
