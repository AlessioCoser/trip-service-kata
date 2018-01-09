'use strict'

let UserSession = require('./UserSession')
let TripDAO = require('./TripDAO')

class TripService {
  constructor (loggedUser = UserSession.getLoggedUser(), tripDAO = TripDAO) {
    this.loggedUser = loggedUser
    this.tripDAO = tripDAO
  }

  getTripsByUser (user) {
    let tripList = []
    let isFriend = false
    if (this.loggedUser != null) {
      let friends = user.getFriends()
      for (let i = 0; i < friends.length; i++) {
        let friend = friends[i]
        if (friend == this.loggedUser) {
          isFriend = true
          break
        }
      }
      if (isFriend) {
        tripList = this.tripDAO.findTripsByUser(user)
      }
      return tripList
    } else {
      throw new Error('User not logged in.')
    }
  }
}

module.exports = TripService
