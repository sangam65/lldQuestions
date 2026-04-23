package ElevatorSystem.entities;

import java.util.HashMap;
import java.util.Map;

import ElevatorSystem.elevatorStrategy.ElevatorStrategy;
import ElevatorSystem.elevatorStrategy.LookUpElevatorStrategy;
import ElevatorSystem.exception.ElevatorException;
import ElevatorSystem.exception.FloorException;

public class Building {
    private final Map<Integer,Floor>floors;
    private final int buildingId;
    
    public int getBuildingId() {
        return buildingId;
    }
    private final Map<Character,Elevator>elevators;
    public Building(int buildingId) {
        this.floors = new HashMap<>();
        this.buildingId=buildingId;

        this.elevators = new HashMap<>();
        this.elevatorStrategy =new LookUpElevatorStrategy();
    }
    private ElevatorStrategy elevatorStrategy;
    public ElevatorStrategy getElevatorStrategy() {
        return elevatorStrategy;
    }
    public synchronized void setElevatorStrategy(ElevatorStrategy elevatorStrategy) {
        this.elevatorStrategy = elevatorStrategy;
    }
    public synchronized Elevator goToFloor(Floor floor){
        Elevator elevator= elevatorStrategy.findElevator(elevators.values().stream().toList(), floor);
        if(elevator==null){
            throw new ElevatorException("No elevator available");
        }
        elevator.addFloor(floor);
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
        Elevator elevator=new Elevator(elevatorId, floors.get(1));
        elevators.put(elevatorId, elevator);
        return elevator;
    }
    

}
