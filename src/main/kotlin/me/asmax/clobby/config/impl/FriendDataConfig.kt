package me.asmax.clobby.config.impl

import me.asmax.clobby.config.AbstractDataConfig
import me.asmax.clobby.config.data.FriendData
import java.util.UUID

class FriendDataConfig : AbstractDataConfig("friends.yml", "Friends") {

    val friends = ArrayList<FriendData>()

    init {
        yaml.getKeys(false).forEach {
            friends.add(
                FriendData(
                    UUID.fromString(yaml.getString("$it.ownerUUID")),
                    UUID.fromString(yaml.getString("$it.friendUUID")),
                    it
                )
            )
        }
    }

    fun addFriend(friendData: FriendData) {
        yaml.set("${friendData.ownerUUID}.friendUUID", friendData.friendUUID.toString())
        yaml.set("${friendData.ownerUUID}.friendName", friendData.friendName)
        save()
        friends.add(friendData)
    }
}