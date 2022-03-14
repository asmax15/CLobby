package me.asmax.clobby.listener

import me.asmax.clobby.util.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class JoinListener: Listener {

    @EventHandler
    fun handleJoin(event: PlayerJoinEvent) {
        val player = event.player

        event.joinMessage = ""

        val gamemodeSelector: ItemStack = ItemBuilder(Material.COMPASS).setName("${ChatColor.GOLD}")
    }
}