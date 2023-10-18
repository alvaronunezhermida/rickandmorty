package com.rickandmorty.app.screens.characters

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rickandmorty.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersAppBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) }
    )
}