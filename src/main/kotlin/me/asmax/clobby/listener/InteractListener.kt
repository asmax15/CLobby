package me.asmax.clobby.listener

import me.asmax.clobby.util.getGamemodeSelector
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class InteractListener : Listener {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if (
            (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) &&
            player.inventory.itemInMainHand.isSimilar(getGamemodeSelector(player.locale()))
        ) {
            // player.openGUI()
        }
    }
}
