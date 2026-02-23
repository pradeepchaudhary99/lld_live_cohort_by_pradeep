//	Parking lot
//  Elevator System
//  Vending machine
//. Load Balancer
//Description:
vehicle enters, system finds available spot, ticket generated, vehicle Parks 
on exit -> calcluate price --> free spot


// Functional Requirements:

// Multiple floors
// multiple parking spot types:
// 1. BIKE, CAR, TRUCK
// Allocate nearest available spot
// Generate ticket on entry
// Calculate fee on exit
// Free spot after exit

// Non Functional:
// Thread Safe 
// Extensible(new vehicle types / pricing strategies)

// Core Entities:

// Vehicle:
// LicenseNumber
// vehicleType

// Parking Spot:
// spotId
// spotType
// isOccupied
// Vehicle

// Ticket:
// ticketId
// vehicle
// spot 
// entryTime
// exitTime 

enum VehicleType {
    BIKE, CAR, TRUCK
}

enum SpotType {
    BIKE, CAR, TRUCK
}

class Vehicle {
    private String licenseNumber;
    private VehicleType type;

    public Vehicle(String licenseNumber, VehicleType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }
}

class ParkingSpot {
    private int spotId;
    private SpotType type;
    private boolean occupied;
    private Vehicle vehicle;

    public ParkingSpot(int spotId, SpotType type) {
        this.spotId = spotId;
        this.type = type;
        this.occupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return !occupied && vehicle.getType().name().equals(type.name());
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }
}


class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public Optional<ParkingSpot> getAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canFitVehicle(vehicle)) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }
}

import java.time.LocalDateTime;
import java.util.UUID;

class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public void closeTicket() {
        this.exitTime = LocalDateTime.now();
    }

    public long getDurationInHours() {
        return java.time.Duration.between(entryTime, exitTime).toHours();
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}

interface PricingStrategy {
    double calculatePrice(long hours);
}

class HourlyPricingStrategy implements PricingStrategy {

    private double ratePerHour;

    public HourlyPricingStrategy(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public double calculatePrice(long hours) {
        return hours * ratePerHour;
    }
}

import java.util.*;

class ParkingLot {

    private List<ParkingFloor> floors;
    private PricingStrategy pricingStrategy;
    private Map<String, Ticket> activeTickets = new HashMap<>();

    public ParkingLot(List<ParkingFloor> floors, PricingStrategy strategy) {
        this.floors = floors;
        this.pricingStrategy = strategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spotOpt = floor.getAvailableSpot(vehicle);
            if (spotOpt.isPresent()) {
                ParkingSpot spot = spotOpt.get();
                spot.parkVehicle(vehicle);
                Ticket ticket = new Ticket(vehicle, spot);
                activeTickets.put(ticket.toString(), ticket);
                return ticket;
            }
        }
        throw new RuntimeException("No spot available");
    }

    public double unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        ticket.closeTicket();
        ticket.getSpot().removeVehicle();
        long hours = ticket.getDurationInHours();
        return pricingStrategy.calculatePrice(hours);
    }
}








