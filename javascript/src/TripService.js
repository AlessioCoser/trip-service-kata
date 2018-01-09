'use strict'

let UserSession = require('./UserSession')
let TripDAO = require('./TripDAO')

class TripService {
  constructor (loggedUser = UserSession.getLoggedUser(), tripDAO = TripDAO) {
    this.loggedUser = loggedUser
    this.tripDAO = tripDAO
  }

  getTripsByUser (user) {
    if (this.loggedUser == null) {
      throw new Error('User not logged in.')
    }

    if (user.isFriendWith(this.loggedUser)) {
      return this.tripDAO.findTripsByUser(user)
    }

    return []
  }
}

module.exports = TripService
