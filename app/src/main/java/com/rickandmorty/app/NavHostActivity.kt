package com.rickandmorty.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.app.screens.character_detail.CharacterDetailScreen
import com.rickandmorty.app.screens.characters.CharactersScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NavHostActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "characters") {
                composable("characters") {
                    CharactersScreen(navController = navController)
                }
                composable("characterDetail") { backStackEntry ->
                    CharacterDetailScreen(navController = navController, backStackEntry.arguments?.getInt("characterId")?:-1)
                }
            }
        }
    }
}