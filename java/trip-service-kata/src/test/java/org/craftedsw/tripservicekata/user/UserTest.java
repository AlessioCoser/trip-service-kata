package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private static User STRANGER = new User();
    private static User FRIEND = new User();

    @Test
    public void returns_false_if_user_is_not_a_friend() throws Exception {
        User user = new User();

        assertFalse(user.isFriendWith(STRANGER));
    }

    @Test
    public void returns_true_if_user_is_a_friend() throws Exception {
        User user = new User();
        user.addFriend(FRIEND);

        assertTrue(user.isFriendWith(FRIEND));
    }
}
