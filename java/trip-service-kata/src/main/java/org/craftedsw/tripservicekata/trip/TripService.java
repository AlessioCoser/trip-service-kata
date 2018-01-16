package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private User loggedUser;
    private TripDAO tripDAO;

    public TripService(User loggedUser, TripDAO tripDAO) {
	    this.loggedUser = loggedUser;
        this.tripDAO = tripDAO;
    }

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		return user.isFriendWith(loggedUser)
				? tripsBy(user)
				: noTrips();
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user) {
		return tripDAO.tripsBy(user);
	}
}
