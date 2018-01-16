package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TripServiceTest {

    private User loggedUser = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        User notLoggedUser = null;

        TripService service = new TripService(notLoggedUser);

        service.getTripsByUser(null);
    }

    @Test
    public void returns_empty_list_if_user_has_no_friends() throws Exception {
        TripService service = new TripService(loggedUser);

        List<Trip> tripsByUser = service.getTripsByUser(new User());

        assertEquals(new ArrayList<Trip>(), tripsByUser);
    }

    @Test
    public void returns_empty_list_if_loggedUser_and_user_are_not_friends() throws Exception {
        TripService service = new TripService(loggedUser);

        User user = userWithFriends();
        List<Trip> trips = service.getTripsByUser(user);

        assertEquals(new ArrayList<Trip>(), trips);
    }

    @Test
    public void returns_trip_list_if_loggedUser_and_user_are_friends() throws Exception {
        TripService service = new TripService(loggedUser);

        Trip trip = new Trip();
        User user = userWithFriendAndTrip(loggedUser, trip);
        List<Trip> trips = service.getTripsByUser(user);

        assertEquals(trip, trips.get(0));
    }

    private User userWithFriends() {
        User user = new User();
        user.addFriend(new User());
        return user;
    }

    private User userWithFriendAndTrip(User friend, Trip trip) {
        User user = new User();
        user.addFriend(friend);
        user.addTrip(trip);
        return user;
    }
}
