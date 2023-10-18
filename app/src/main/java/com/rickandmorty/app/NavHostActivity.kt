package com.rickandmorty.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rickandmorty.app.screens.character_detail.CharacterDetailScreen
import com.rickandmorty.app.screens.characters.CharactersScreen
import com.rickandmorty.app.screens.error.ErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "characters") {
                composable("characters") {
                    CharactersScreen(
                        onClick = { characterId ->
                            navController.navigate(
                                route = "characterDetail/$characterId"
                            )
                        }
                    )
                }
                composable("characterDetail/{characterId}",
                    arguments = listOf(
                        navArgument("characterId") {
                            type = IntType
                        }
                    )) {
                    CharacterDetailScreen(navController = navController)
                }
                composable("errorDialog/{errorMessage}",
                    arguments = listOf(
                        navArgument("errorMessage") {
                            type = StringType
                        }
                    )) {
                    ErrorDialog(
                        navController = navController,
                        it.arguments?.getString("errorMessage") ?: ""
                    )
                }
            }
        }
    }
}