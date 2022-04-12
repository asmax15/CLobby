package me.asmax.clobby.scoreboard

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

abstract class ScoreboardBuilder(player: Player, displayName: String) {

    var scoreboard: Scoreboard
    var objective: Objective

    var player: Player

    init {
        this.player = player

        if (player.scoreboard.equals(Bukkit.getScoreboardManager().mainScoreboard)) {
            player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        }

        this.scoreboard = player.scoreboard

        if (this.scoreboard.getObjective("display") != null) {
            this.scoreboard.getObjective("display")!!.unregister()
        }

        this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName)
        this.objective.displaySlot = DisplaySlot.SIDEBAR

        createScoreboard()
    }

    abstract fun createScoreboard()

    abstract fun update()

    fun setDisplayName(displayName: String) { this.objective.displayName = displayName }

    fun setScore(content: String, score: Int) {
        val team: Team = getTeamByScore(score)

        if (team == null) {
            return
        }

        team.prefix = content
        showScore(score)
    }

    fun removeScore(score: Int) { hideScore(score) }


}