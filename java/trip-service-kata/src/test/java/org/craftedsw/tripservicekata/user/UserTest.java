package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {
    @Test
    public void returns_false_when_2_users_are_not_friends() throws Exception {
        User user = new User();
        User stranger = new User();

        assertFalse(user.isFriendOf(stranger));
    }

    @Test
    public void returns_true_when_2_users_are_friends() throws Exception {
        User friend = new User();
        User user = new User();
        user.addFriend(friend);

        assertTrue(user.isFriendOf(friend));
    }
}
