package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TripServiceTest {

    private User notLoggedUser = null;
    private User aLoggedUser = new User();
    private User anUser = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        TripService tripService = new TripService(notLoggedUser);

        tripService.getTripsByUser(null);
    }

    @Test
    public void returns_empty_list_if_user_has_no_friends() throws Exception {
        TripService tripService = new TripService(aLoggedUser);

        List<Trip> tripsByUser = tripService.getTripsByUser(anUser);

        assertEquals(0, tripsByUser.size());
    }

    @Test
    public void returns_empty_list_if_loggedUser_and_user_are_not_friends() throws Exception {
        TripService tripService = new TripService(aLoggedUser);

        User userWithFriends = anUserWithFriends();
        List<Trip> tripsByUser = tripService.getTripsByUser(userWithFriends);

        assertEquals(0, tripsByUser.size());
    }

    @Test
    public void returns_trip_list_if_loggedUser_and_user_are_friends() throws Exception {
        TripService tripService = new TestableTripService(aLoggedUser);

        User friendUser = anUserWithATripAndFriendOf(aLoggedUser);
        List<Trip> tripsByUser = tripService.getTripsByUser(friendUser);

        assertEquals(1, tripsByUser.size());
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