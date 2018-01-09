'use strict'

const assert = require('assert')
const TripService = require('../src/TripService')

let loggedUser = { name: 'Logged User' }
let anotherUser = { name: 'Another User' }

describe('TripService', () => {
  it('throws an error if user is not logged in', () => {
    let tripService = new TripService(null)

    assert.throws(() => tripService.getTripsByUser(null), /User not logged in./)
  })

  it('returns empty array when loggedUser is not a friend', () => {
    let sampleUser = { name: 'Sample User', getFriends: () => [anotherUser] }
    let tripService = new TripService(loggedUser)

    assert.deepEqual([], tripService.getTripsByUser(sampleUser))
  })
})
