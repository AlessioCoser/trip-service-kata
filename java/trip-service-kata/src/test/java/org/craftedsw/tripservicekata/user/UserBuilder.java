package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

/**
 * Created by alessiocoser on 16/01/2018.
 */
public class UserBuilder {
    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    private Trip[] trips = new Trip[]{};
    private User[] friends = new User[]{};

    public UserBuilder friendWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addFriendTo(user);
        addTripsTo(user);

        return user;
    }

    private void addTripsTo(User user) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }

    private void addFriendTo(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }
}
