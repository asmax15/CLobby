package me.asmax.clobby.extension

import me.asmax.clobby.CLobby
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

fun Player.sendPrefixMessage(meessage: String) = sendPrefixMessage(Component.text(meessage))

fun Player.sendPrefixMessage(message: Component) = sendMessage(CLobby.prefix.append(message))
