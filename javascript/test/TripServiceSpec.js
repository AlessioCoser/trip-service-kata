'use strict'

let assert = require('assert')
let TripService = require('../src/TripService')

describe('TripService', () => {
  it('throws an error if user is not logged in', () => {
    let tripService = new TripService(null)

    assert.throws(() => tripService.getTripsByUser(null), /User not logged in./)
  })
})
