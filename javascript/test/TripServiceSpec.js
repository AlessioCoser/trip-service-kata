'use strict'

const assert = require('assert')
const User = require('../src/User')
const TripService = require('../src/TripService')

let aTrip = 'Barcellona'
let loggedUser = new User([])
let anotherUser = new User([])

describe('TripService', () => {
  it('throws an error if user is not logged in', () => {
    let tripService = new TripService(null)

    assert.throws(() => tripService.getTripsByUser(null), /User not logged in./)
  })

  it('returns empty array when loggedUser is not a friend', () => {
    let sampleUser = new User([anotherUser])
    let tripService = new TripService(loggedUser)

    assert.deepEqual([], tripService.getTripsByUser(sampleUser))
  })

  it('returns a trip list when loggedUser is a friend', () => {
    let FakeTripDAO = {findTripsByUser: (user) => [aTrip]}

    let sampleUser = new User([loggedUser])
    let tripService = new TripService(loggedUser, FakeTripDAO)

    assert.deepEqual([aTrip], tripService.getTripsByUser(sampleUser))
  })
})
