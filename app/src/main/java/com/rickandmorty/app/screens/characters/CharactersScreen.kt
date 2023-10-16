package com.rickandmorty.app.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(navController: NavHostController) {
    val viewModel = viewModel<CharactersViewModel>()
    val charactersList by viewModel.charactersState.collectAsState()
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { CharactersAppBar() }
            ) { padding ->
                CharactersList(
                    characters = charactersList,
                    onClick = {
                        viewModel.onCharacterClicked(it)
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}