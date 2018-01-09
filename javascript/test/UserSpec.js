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

    it('return friend list with some friends', () => {
      let friend = new User()
      let user = new User([friend])

      assert.deepEqual([friend], user.getFriends())
    })
  })

  describe('isFriendWith()', () => {
    let aFriend = new User()

    it('return false if they are not friends', () => {
      let aStranger = new User()
      let user = new User([aFriend])

      assert.equal(false, user.isFriendWith(aStranger))
    })

    it('return true if they are friends', () => {
      let user = new User([aFriend])

      assert.equal(true, user.isFriendWith(aFriend))
    })
  })
})
