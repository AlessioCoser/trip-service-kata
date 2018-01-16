package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TripServiceTest {

    private User notLoggedUser = null;
    private User anUser = new User();
    private User aLoggedUser = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        TripService tripService = new TripService(notLoggedUser);

        tripService.getTripsByUser(null);
    }

    @Test
    public void returns_empty_list_if_user_has_no_friends() throws Exception {
        TripService tripService = new TripService(aLoggedUser);

        List<Trip> tripsByUser = tripService.getTripsByUser(anUser);

        List<Trip> emptyTripList = new ArrayList<Trip>();
        assertEquals(emptyTripList, tripsByUser);
    }

    @Test
    public void returns_empty_list_if_loggedUser_and_user_are_not_friends() throws Exception {
        TripService tripService = new TripService(aLoggedUser);

        User userWithFriends = anUserWithFriends();
        List<Trip> tripsByUser = tripService.getTripsByUser(userWithFriends);

        List<Trip> emptyTripList = new ArrayList<Trip>();
        assertEquals(emptyTripList, tripsByUser);
    }

    private User anUserWithFriends() {
        User user = new User();
        user.addFriend(new User());
        return user;
    }
}
