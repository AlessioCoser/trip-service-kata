package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        TripService tripService = new TestableTripService();

        tripService.getTripsByUser(null);
    }
}

class TestableTripService extends TripService {

    @Override
    protected User getLoggedUser() {
        return null;
    }
}
