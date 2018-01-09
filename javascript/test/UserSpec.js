'use strict'

const assert = require('assert')
const User = require('../src/User')

describe('User', () => {
  describe('getFriends()', () => {
    it('return empty array with no friends', () => {
      let noFriends = []
      let user = new User(noFriends)

      assert.deepEqual([], user.getFriends())
    })
  })
})
