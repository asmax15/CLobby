package me.asmax.clobby.util

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import me.asmax.clobby.extension.addComponent
import me.asmax.clobby.extension.render
import net.axay.kspigot.items.addLore
import net.axay.kspigot.items.flags
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.space
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.Component.translatable
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.Locale
import java.util.UUID

fun getTextureHead(b64: String, name: Component): ItemStack {
    val head = itemStack(Material.PLAYER_HEAD) {
        meta {
            this.name = name

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }
    val meta = head.itemMeta
    try {
        val profileField = meta.javaClass.getDeclaredField("profile")
        profileField.isAccessible = true
        profileField.set(meta, textureProfile(b64))
    } catch (exception: NoSuchFieldException) {
        exception.printStackTrace()
    } catch (exception: IllegalArgumentException) {
        exception.printStackTrace()
    } catch (exception: IllegalAccessException) {
        exception.printStackTrace()
    }
    head.itemMeta = meta
    return head
}

private fun textureProfile(b64: String): GameProfile {
    val id = UUID(
        b64.substring(b64.length - 20).hashCode().toLong(),
        b64.substring(b64.length - 10).hashCode().toLong()
    )
    val profile = GameProfile(id, "Player")
    profile.properties.put("textures", Property("textures", b64))
    return profile
}

fun getGamemodeSelector(locale: Locale) =
    itemStack(Material.COMPASS) {
        meta {
            name = translatable("GameModeSelector.name")
                .color(Colors.LOBBYITEMS)
                .render(locale)

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }

fun getLobbySwitcher(locale: Locale) =
    itemStack(Material.NETHER_STAR) {
        meta {
            name = translatable("LobbySwitcher.name")
                .color(Colors.LOBBYITEMS)
                .render(locale)

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }

fun getSettings(locale: Locale) =
    itemStack(Material.COMPARATOR) {
        meta {
            name = translatable("Settings.name")
                .color(Colors.LOBBYITEMS)
                .render(locale)

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }

fun getYourInventory(locale: Locale) =
    itemStack(Material.CHEST) {
        meta {
            name = translatable("YourInventory.name")
                .color(Colors.LOBBYITEMS)
                .render(locale)

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }

fun generateFriendStatusItem(locale: Locale) =
    itemStack(Material.SKULL_BANNER_PATTERN) {
        meta {
            name = translatable("Friends.status")
                .color(Colors.LOBBYITEMS)
                .render(locale)

            addLore {
                addComponent(
                    translatable("Friends.status.lore")
                        .color(Colors.SECONDARY)
                        .render(locale)
                )
            }

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }

fun generateItemForFriend(player: OfflinePlayer, locale: Locale): ItemStack {
    val online = player.isOnline
    val onlineLore: Component

    if (online) {
        onlineLore = translatable("Friends.friend.online.online")
            .color(Colors.SUCCESS)
            .render(locale)
    } else {
        onlineLore = translatable("Friends.friend.online.offline")
            .color(Colors.ERROR)
            .render(locale)
    }

    val head = itemStack(Material.PLAYER_HEAD) {
        meta {
            name = text(player.name.toString())
                .color(Colors.FRIEND)

            addLore {
                addComponent(onlineLore)
            }
        }
    }
    val meta = head.itemMeta
    (meta as SkullMeta).owningPlayer = player
    head.itemMeta = meta
    return head
}

fun getYourFriends(player: OfflinePlayer, locale: Locale): ItemStack {
    val head = itemStack(Material.PLAYER_HEAD) {
        meta {
            name = translatable("YourFriends.name")
                .color(Colors.LOBBYITEMS)
                .render(locale)

            flags(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_DYE,
                ItemFlag.HIDE_PLACED_ON,
                ItemFlag.HIDE_POTION_EFFECTS,
                ItemFlag.HIDE_UNBREAKABLE
            )
        }
    }
    val meta = head.itemMeta
    (meta as SkullMeta).owningPlayer = player
    head.itemMeta = meta
    return head
}

val placeHolderItemGray =
    itemStack(Material.GRAY_STAINED_GLASS_PANE) {
        meta {
            name = space()
        }
    }

val placeHolderItemWhite =
    itemStack(Material.WHITE_STAINED_GLASS_PANE) {
        meta {
            name = space()
        }
    }
