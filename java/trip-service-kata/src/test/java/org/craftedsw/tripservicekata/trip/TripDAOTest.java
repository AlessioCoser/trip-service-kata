package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {
    
    @Test(expected = CollaboratorCallException.class)
    public void thows_a_an_exception_when_retrieving_user_trips() throws Exception {
        TripDAO tripDAO = new TripDAO();

        tripDAO.tripsBy(new User());
    }
}
