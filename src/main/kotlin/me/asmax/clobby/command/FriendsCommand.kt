package me.asmax.clobby.command

import com.mojang.brigadier.arguments.StringArgumentType
import me.asmax.clobby.config.Config
import me.asmax.clobby.config.data.FriendData
import me.asmax.clobby.extension.successTranslatable
import me.asmax.clobby.util.Permissions
import net.axay.kspigot.commands.argument
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.getArgument
import net.axay.kspigot.commands.requiresPermission
import net.axay.kspigot.commands.runs
import net.axay.kspigot.commands.suggestListSuspending
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

class FriendsCommand {

    fun register() = command("friends", true) {
        requiresPermission(Permissions.FRIENDS_PERMISSION)
        argument("name", StringArgumentType.string()) {
            suggestListSuspending { suggest ->
                Config.friendDataConfig.friends.map { it.friendName }.filter {
                    if (suggest.input != null && suggest.input.substring(suggest.input.length - 1) != " ")
                        it.startsWith(suggest.getArgument<String>("name"), true) else
                        true
                }.sorted()
            }
            runs {
                if (!Config.friendDataConfig.friends.any { it.friendName == getArgument<String>("name").lowercase() }) {
                    requiresPermission(Permissions.FRIEND_ADD)
                    val name = getArgument<String>("name").lowercase()
                    val ownerUUID = player.uniqueId
                    val friendUUID = Bukkit.getOfflinePlayer(getArgument<String>("name").lowercase()).uniqueId
                    val friend: OfflinePlayer = Bukkit.getOfflinePlayer(getArgument<String>("name").lowercase())
                    Config.friendDataConfig.addFriend(
                        FriendData(
                            ownerUUID,
                            friendUUID,
                            name
                        )
                    )
                    player.sendMessage(
                        successTranslatable(
                            "Friend.add",
                            player.name()
                        )
                    )
                } else {
                    requiresPermission(Permissions.FRIEND_SHOW)
                    val friend = Config.friendDataConfig.friends.find {
                        it.friendName == getArgument<String>("name")
                    } ?: return@runs
                    val friendUUID = friend.friendUUID
                    // TODO: Open a GUI with the profile of the player
                }
            }
        }
    }
}
