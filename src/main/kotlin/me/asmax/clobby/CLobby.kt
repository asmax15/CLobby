package me.asmax.clobby

import me.asmax.clobby.command.DefaultLanguageCommand
import me.asmax.clobby.command.FriendsCommand
import me.asmax.clobby.config.Config
import me.asmax.clobby.extension.asTextColor
import me.asmax.clobby.extension.coloredString
import me.asmax.clobby.i18n.TranslationsProvider
import me.asmax.clobby.listener.ConnectionListener
import me.asmax.clobby.util.Colors
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.Component.translatable

class CLobby : KSpigot() {

    companion object {
        val prefix: Component =
            literalText {
                component(
                    literalText {
                        text("Lobby")
                        color = KColors.WHITE.asTextColor()
                        bold = true
                    }
                )
                component(Component.space())
                component(
                    literalText {
                        text("|")
                        color = KColors.GRAY.asTextColor()
                    }
                )
                component(Component.space())
            }
        val translationsProvider = TranslationsProvider()
    }

    override fun load() {
        Config()
    }

    override fun startup() {
        if (onlinePlayers.isNotEmpty()) {
            logger.severe("It looks like you've reloaded, please restart instead!")
        }

        Config.reloadFriends()

        translationsProvider.registerTranslations()

        pluginManager.registerEvents(ConnectionListener(), this)

        DefaultLanguageCommand().register()
        FriendsCommand().register()

        val pluginDescription = this.description

        logger.info(
            translatable(
                "console.enabled.authors"
            )
                .color(Colors.SECONDARY)
                .args(
                    text(
                        if (pluginDescription.authors.size <= 1) {
                            pluginDescription.authors.joinToString("")
                        } else {
                            val authors = pluginDescription.authors.sorted().toMutableList()
                            authors.add(
                                authors.size - 1,
                                translatable("generic.and").coloredString()
                            )
                            authors.joinToString(" ")
                        }
                    )
                )
                .color(Colors.PRIMARY)
                .coloredString()
        )
        if (pluginDescription.website != null) {
            logger.info(
                translatable(
                    "console.enabled.website"
                )
                    .color(Colors.SECONDARY)
                    .args(
                        text(pluginDescription.website ?: "n/a")
                            .color(Colors.PRIMARY)
                    )
                    .coloredString()
            )
        }
    }

    override fun shutdown() {
        onlinePlayers.forEach {
            it.inventory.clear()
        }
    }
}
