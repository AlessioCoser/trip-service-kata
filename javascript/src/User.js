'use strict'

module.exports = class User {
  constructor (friends) {
    this._friends = friends || []
  }

  getFriends () {
    return this._friends
  }

  isFriendWith (stranger) {
    let isFriend = false
    let friends = this.getFriends()

    for (let i = 0; i < friends.length; i++) {
      let friend = friends[i]
      if (friend == stranger) {
        isFriend = true
        break
      }
    }

    return isFriend
  }
}
