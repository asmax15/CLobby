package me.asmax.clobby.listener

import me.asmax.clobby.util.getGamemodeSelector
import me.asmax.clobby.util.getLobbySwitcher
import me.asmax.clobby.util.getSettings
import me.asmax.clobby.util.getYourFriends
import me.asmax.clobby.util.getYourInventory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class ConnectionListener : Listener {

    @EventHandler
    fun handleJoin(event: PlayerJoinEvent) {
        val player = event.player

        event.joinMessage(null)

        player.inventory.clear()

        player.inventory.setItem(0, getGamemodeSelector(player.locale()))
        player.inventory.setItem(1, getLobbySwitcher(player.locale()))
        player.inventory.setItem(4, getYourInventory(player.locale()))
        player.inventory.setItem(7, getYourFriends(player, player.locale()))
        player.inventory.setItem(8, getSettings(player.locale()))
    }

    @EventHandler
    fun handleQuit(event: PlayerQuitEvent) {
        event.quitMessage(null)
    }
}
