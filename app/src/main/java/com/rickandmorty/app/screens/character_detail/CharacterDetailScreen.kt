package com.rickandmorty.app.screens.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.rickandmorty.app.R
import com.rickandmorty.app.ui.theme.AppTheme
import com.rickandmorty.domain.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val character: Character? by viewModel.characterState.collectAsState()
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = character?.name ?: "") },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Go Back"
                                )
                            }
                        }
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { padding ->
                Column {
                    Image(
                        painter = rememberImagePainter(data = character?.image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxWidth()
                            .height(270.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = character?.species ?: "",
                            modifier = Modifier
                                .alpha(0.8f)
                                .padding(vertical = 12.dp),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        character?.location?.name?.takeIf { it.isNotEmpty() }?.let {
                            CharacterProperty(
                                label = stringResource(id = R.string.location),
                                value = it
                            )
                        }
                        character?.origin?.name?.takeIf { it.isNotEmpty() }?.let {
                            CharacterProperty(
                                label = stringResource(id = R.string.origin),
                                value = it
                            )
                        }
                        character?.status?.takeIf { it.isNotEmpty() }?.let {
                            CharacterProperty(
                                label = stringResource(id = R.string.status),
                                value = it
                            )
                        }
                        character?.gender?.takeIf { it.isNotEmpty() }?.let {
                            CharacterProperty(
                                label = stringResource(id = R.string.gender),
                                value = it
                            )
                        }
                        character?.type?.takeIf { it.isNotEmpty() }?.let {
                            CharacterProperty(
                                label = stringResource(id = R.string.type),
                                value = it
                            )
                        }
                    }
                }
            }
        }
    }
}