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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rickandmorty.app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(
    navController: NavHostController,
    onClick: (Int) -> Unit,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val charactersList by viewModel.charactersState.collectAsState()
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { CharactersAppBar() },
                modifier = Modifier.fillMaxSize()
            ) { padding ->
                CharactersList(
                    characters = charactersList,
                    onClick = {
                        onClick(it.id)
                    },
                    loadMore = {
                        viewModel.loadMore()
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}