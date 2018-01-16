package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private User loggedUser;

	public TripService(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		List<Trip> tripList = new ArrayList<Trip>();

		if (user.isFriendOf(loggedUser)) {
			tripList = user.trips();
		}

		return tripList;
	}
}
