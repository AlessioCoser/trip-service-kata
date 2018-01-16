package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    private static User GUEST = null;
    private static User LOGGED_USER = UserBuilder.aUser().build();
    private static User ANOTHER_USER = UserBuilder.aUser().build();

    @Mock
    private TripDAO dao;

    @Test(expected = UserNotLoggedInException.class)
    public void thows_a_not_logged_in_exception() throws Exception {
        TripService tripService = new TripService(GUEST, dao);

        tripService.getTripsByUser(null);
    }

    @Test
    public void returns_empty_list_if_user_has_no_friends() throws Exception {
        TripService tripService = new TripService(LOGGED_USER, dao);

        List<Trip> friendTrips = tripService.getTripsByUser(ANOTHER_USER);

        assertEquals(0, friendTrips.size());
    }

    @Test
    public void returns_empty_list_if_loggedUser_and_user_are_not_friends() throws Exception {
        TripService tripService = new TripService(LOGGED_USER, dao);

        User friend = UserBuilder.aUser()
                .friendWith(ANOTHER_USER)
                .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertEquals(0, friendTrips.size());
    }

    @Test
    public void returns_trip_list_if_loggedUser_and_user_are_friends() throws Exception {
        User friend = UserBuilder.aUser()
                .friendWith(LOGGED_USER)
                .withTrips(new Trip(), new Trip())
                .build();

        when(dao.tripsBy(any(User.class))).thenReturn(friend.trips());

        TripService tripService = new TripService(LOGGED_USER, dao);

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertEquals(2, friendTrips.size());
    }

}