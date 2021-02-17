package event.crew_seat;

import base.CrewMember;

public class SeatAssignCrewMember {
	CrewMember crewMember;

	public SeatAssignCrewMember(CrewMember crewMember) {
		this.crewMember = crewMember;
	}

	public CrewMember getCrewMember() {
		return crewMember;
	}

	public String toString() {
		return "Event: CrewSeat - SeatAssign - CrewMember";
	}
}
