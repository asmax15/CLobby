package me.asmax.clobby.command

import me.asmax.clobby.CLobby
import me.asmax.clobby.config.Config
import me.asmax.clobby.extension.sendPrefixMessage
import me.asmax.clobby.extension.successTranslatable
import me.asmax.clobby.util.Permissions
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.literal
import net.axay.kspigot.commands.requiresPermission
import net.axay.kspigot.commands.runs
import net.kyori.adventure.text.Component.text

class DefaultLanguageCommand {

    fun register() = command("default-language", true) {
        requiresPermission(Permissions.LANGUAGE_COMMAND)
        runs {
            player.sendPrefixMessage(
                successTranslatable("Language.current", text(Config.languageConfig.defaultLanguage.displayLanguage))
            )
        }
        CLobby.translationsProvider.locales.forEach {
            literal(it.displayLanguage) {
                runs {
                    Config.languageConfig.defaultLanguage = it
                    player.sendPrefixMessage(successTranslatable("Language.set", text(literal)))
                }
            }
        }
    }
}
