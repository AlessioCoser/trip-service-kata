package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TripServiceTest {

    private static User GUEST = null;
    private static User LOGGED_USER = new User();
    private static User ANOTHER_USER = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        TripService tripService = new TestableTripService(GUEST);

        tripService.getTripsByUser(null);
    }

    @Test
    public void returns_empty_list_if_user_has_no_friends() throws Exception {
        TripService tripService = new TestableTripService(LOGGED_USER);

        List<Trip> friendTrips = tripService.getTripsByUser(ANOTHER_USER);

        assertEquals(0, friendTrips.size());
    }

    @Test
    public void returns_empty_list_if_loggedUser_and_user_are_not_friends() throws Exception {
        TripService tripService = new TestableTripService(LOGGED_USER);

        User friend = anUserWithFriends();
        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertEquals(0, friendTrips.size());
    }

    @Test
    public void returns_trip_list_if_loggedUser_and_user_are_friends() throws Exception {
        TripService tripService = new TestableTripService(LOGGED_USER);

        User friend = anUserWithATripAndFriendOf(LOGGED_USER);
        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertEquals(1, friendTrips.size());
    }

    private User anUserWithFriends() {
        User user = new User();
        user.addFriend(new User());
        return user;
    }

    private User anUserWithATripAndFriendOf(User friend) {
        User user = new User();
        user.addFriend(friend);
        user.addTrip(new Trip());
        return user;
    }

    private class TestableTripService extends TripService {
        public TestableTripService(User loggedUser) {
            super(loggedUser);
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.trips();
        }
    }
}