package me.asmax.clobby.util

import me.asmax.clobby.extension.asTextColor
import net.axay.kspigot.chat.KColors

object Colors {

    val PRIMARY = KColors.DARKBLUE.asTextColor()
    val SECONDARY = KColors.GRAY.asTextColor()

    val ACTIVE = KColors.LIMEGREEN.asTextColor()
    val INACTIVE = KColors.INDIANRED.asTextColor()

    val ERROR = KColors.RED.asTextColor()
    val ERROR_ARGS = INACTIVE
    val SUCCESS = KColors.DARKGREEN.asTextColor()
    val SUCCESS_SECONDARY = ACTIVE

    val CONFIGURATION = KColors.MISTYROSE.asTextColor()
}