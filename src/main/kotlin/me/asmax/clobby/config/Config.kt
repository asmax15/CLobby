package me.asmax.clobby.config

import me.asmax.clobby.config.impl.LanguageConfig

object Config {

    lateinit var languageConfig: LanguageConfig

    operator fun invoke() {
        reloadConfig()
    }

    private fun reloadConfig() {
        languageConfig = LanguageConfig()
    }
}