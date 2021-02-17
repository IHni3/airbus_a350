package event.economy_class_seat;

import base.Passenger;

public class SeatAssignPassenger {
	Passenger passenger;

	public SeatAssignPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public String toString() {
		return "Event: EconomyClassSeat - SeatAssign - Passenger";
	}
}
