package event.business_class_seat;

import base.Passenger;

public class SeatAssignPassenger {
	Passenger passenger;

	public SeatAssignPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public String toString() {
		return "Event: BusinessClassSeat - SeatAssign - Passenger";
	}
}
