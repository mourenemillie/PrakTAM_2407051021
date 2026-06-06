package com.example.praktam_2407051021.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{journalId}") {
        fun createRoute(journalId: String) = "detail/$journalId"
    }
    object AddEdit : Screen("add_edit/{journalId}") {
        fun createRoute(journalId: String?) = "add_edit/${journalId ?: "new"}"
    }
    object Appearance : Screen("appearance")
    object MoodHistory : Screen("mood_history")
    object MusicSoundtrack : Screen("music_soundtrack")
}
