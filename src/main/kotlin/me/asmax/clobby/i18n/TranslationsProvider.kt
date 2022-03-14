package me.asmax.clobby.i18n

import net.kyori.adventure.key.Key
import net.kyori.adventure.translation.GlobalTranslator
import net.kyori.adventure.translation.TranslationRegistry
import java.util.Locale
import java.util.ResourceBundle

class TranslationsProvider {

    val key = Key.key("me.asmax.clobby")

    private val translationRegistry = TranslationRegistry.create(key).apply {
        defaultLocale(Locale.US)
    }

    val locales = listOf(Locale.US, Locale.GERMANY)

    fun setDefault(locale: Locale) {
        translationRegistry.defaultLocale(locale)
    }

    fun registerTranslations() {
        val bundleNames = mutableListOf(
            "translations.general.general",
            "translations.general.items",
            "translations.general.messages",
        )
        bundleNames.apply {

        }

        locales.forEach { locale ->
            bundleNames.forEach { bundleName ->
                translationRegistry.registerAll(
                    locale,
                    ResourceBundle.getBundle(bundleName, locale),
                    false
                )
            }
        }
        GlobalTranslator.get().addSource(translationRegistry)
    }
}
