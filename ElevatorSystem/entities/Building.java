package ElevatorSystem.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ElevatorSystem.elevatorStrategy.ElevatorStrategy;
import ElevatorSystem.enums.Direction;
import ElevatorSystem.exception.ElevatorException;
import ElevatorSystem.exception.FloorException;

public class Building {
    private final Map<Integer,Floor>floors;
    private final int buildingId;
    private Floor startingFloor;
    
    
    public int getBuildingId() {
        return buildingId;
    }
    private final Map<Character,Elevator>elevators;
    public Building(int buildingId,ElevatorStrategy elevatorStrategy) {
        this.floors = new HashMap<>();
        this.buildingId=buildingId;
        this.startingFloor=new Floor(0, this);
        this.floors.put(0,startingFloor);

        this.elevators = new HashMap<>();
        this.elevatorStrategy =elevatorStrategy;
    }
    private ElevatorStrategy elevatorStrategy;
    public ElevatorStrategy getElevatorStrategy() {
        return elevatorStrategy;
    }
    public synchronized void setElevatorStrategy(ElevatorStrategy elevatorStrategy) {
        this.elevatorStrategy = elevatorStrategy;
    }
    public synchronized Elevator goToFloor(Floor destinationFloor,Floor currentFloor)throws ElevatorException{
        List<Elevator>elevatorList=elevators.values().stream().toList();
        if(elevatorList.isEmpty()){
            throw new ElevatorException("Elevator not added in building");
        }
        if(!floors.containsKey(destinationFloor.getFloorNumber())||!floors.containsKey(currentFloor.getFloorNumber())){
             throw new FloorException("Floor not found");
        }
        if(currentFloor==destinationFloor){
            throw new FloorException("Already on floor "+destinationFloor.getFloorNumber());
        }
        Direction direction=destinationFloor.getFloorNumber()>currentFloor.getFloorNumber()?Direction.UP:Direction.DOWN;
        Elevator elevator= elevatorStrategy.findElevator(elevatorList, currentFloor,destinationFloor,direction);
        if(elevator==null){
            throw new ElevatorException("No elevator available");
        }
        elevator.addFloor(destinationFloor);
        return elevator;
    }

    public synchronized Floor addFloor(int floorNumber) throws FloorException{
        if(floors.containsKey(floorNumber)){
            throw new FloorException("Floor is already added");
        }
        Floor floor=new Floor(floorNumber,this);
        floors.put(floorNumber, floor);
        return floor;
    }
    public synchronized Elevator addElevator(char elevatorId) throws ElevatorException{
        if(elevators.containsKey(elevatorId)){
            throw new ElevatorException("Elevator is already added");
        }
        Elevator elevator=new Elevator(elevatorId, startingFloor);
        elevators.put(elevatorId, elevator);
        return elevator;
    }
    

}
