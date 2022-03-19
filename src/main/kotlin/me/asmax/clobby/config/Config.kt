package me.asmax.clobby.config

import me.asmax.clobby.config.impl.FriendDataConfig
import me.asmax.clobby.config.impl.LanguageConfig

object Config {

    lateinit var languageConfig: LanguageConfig
    lateinit var friendDataConfig: FriendDataConfig

    operator fun invoke() {
        reloadConfig()
    }

    private fun reloadConfig() {
        languageConfig = LanguageConfig()
        friendDataConfig = FriendDataConfig()
    }

    fun reloadFriends() {
        friendDataConfig = FriendDataConfig()
    }
}
