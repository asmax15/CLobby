package me.asmax.clobby.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class QuitListener : Listener {

    @EventHandler
    fun handleQuit(event: PlayerQuitEvent) {
        event.quitMessage = ""
    }
}