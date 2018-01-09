'use strict'

module.exports = class User {
  constructor (friends) {
    this._friends = friends || []
  }

  getFriends () {
    return this._friends
  }
}
