package org.enestigli.samplechatbot.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.enestigli.samplechatbot.presentation.FirstScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "first_screen") {

        composable("first_screen"){
            FirstScreen()
        }
    }
}