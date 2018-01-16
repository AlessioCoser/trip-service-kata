package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class UserTest {
    @Test
    public void return_false_when_2_users_are_not_friends() throws Exception {
        User user = new User();
        User stranger = new User();

        assertFalse(user.isFriendOf(stranger));
    }
}
