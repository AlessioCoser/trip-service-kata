package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TripServiceTest {

    private User notLoggedUser = null;


    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        TripService tripService = new TripService(notLoggedUser);

        tripService.getTripsByUser(null);
    }

    @Test
    public void returns_empty_list_if_user_has_no_friends() throws Exception {
        User aLoggedUser = new User();
        TripService tripService = new TripService(aLoggedUser);

        User anUser = new User();
        List<Trip> tripsByUser = tripService.getTripsByUser(anUser);

        List<Trip> emptyTripList = new ArrayList<Trip>();
        assertEquals(emptyTripList, tripsByUser);
    }
}
