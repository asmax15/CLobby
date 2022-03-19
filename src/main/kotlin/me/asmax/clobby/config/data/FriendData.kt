package me.asmax.clobby.config.data

import java.util.UUID

data class FriendData(
    val ownerUUID: UUID,
    val friendUUID: UUID,
    val friendName: String,
)
