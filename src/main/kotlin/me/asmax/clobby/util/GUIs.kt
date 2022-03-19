package me.asmax.clobby.util

import me.asmax.clobby.extension.coloredString
import me.asmax.clobby.extension.render
import net.axay.kspigot.gui.ForInventoryFiveByNine
import net.axay.kspigot.gui.ForInventorySixByNine
import net.axay.kspigot.gui.GUI
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.gui.rectTo
import net.kyori.adventure.text.Component
import java.util.Locale

object GUIPage {
    const val gamemodePageNumber = 0
    const val friendsPageNumber = -1
}

fun gamemodeGUI(locale: Locale): GUI<ForInventoryFiveByNine> = kSpigotGUI(GUIType.FIVE_BY_NINE) {
    title = Component.translatable("Settings.name")
        .color(Colors.GUI)
        .render(locale)
        .coloredString()
    defaultPage = GUIPage.gamemodePageNumber

    page(GUIPage.gamemodePageNumber) {
        placeholder(Slots.Border, placeHolderItemGray)
        placeholder(Slots.RowTwoSlotTwo rectTo Slots.RowFourSlotEight, placeHolderItemWhite)

        //TODO: Add GameModeSwitcher GUI
    }
}

fun friendsGUI(locale: Locale): GUI<ForInventorySixByNine> = kSpigotGUI(GUIType.SIX_BY_NINE) {
    title = Component.translatable("YourFriends.name")
        .color(Colors.FRIENDSGUI)
        .render(locale)
        .coloredString()
    defaultPage =  GUIPage.friendsPageNumber

    page(GUIPage.friendsPageNumber) {
        placeholder(Slots.Border, placeHolderItemGray)
        placeholder(Slots.RowTwoSlotTwo rectTo Slots.RowFourSlotEight, placeHolderItemWhite)

        /*val compound = createRectCompound<OfflinePlayer>(
            Slots.RowTwoSlotTwo,
            Slots.RowFourSlotEight,

        )*/
    }
}
