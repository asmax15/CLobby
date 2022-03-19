package me.asmax.clobby.config.impl

import me.asmax.clobby.CLobby
import me.asmax.clobby.config.AbstractConfig
import java.util.Locale

class LanguageConfig : AbstractConfig("languages.yml") {

    private val defaultLocale = Locale.ENGLISH

    var defaultLanguage: Locale
        get() = Locale((getSetting("default") ?: defaultLocale.language) as String)
        set(value) {
            setSetting("default", value.language)
            CLobby.translationsProvider.setDefault(value)
        }

    fun setSetting(setting: String, value: Any?) {
        yaml.set(setting, value)
        save()
    }

    fun getSetting(setting: String): Any? {
        return yaml.get(setting)
    }
}
