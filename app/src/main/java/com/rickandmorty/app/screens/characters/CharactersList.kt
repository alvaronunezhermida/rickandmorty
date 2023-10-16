package com.rickandmorty.app.screens.characters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.rickandmorty.app.R
import com.rickandmorty.domain.Character

@ExperimentalFoundationApi
@Composable
fun CharactersList(
    characters: List<Character>,
    onClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(8.dp),
            content = {
                items(characters) { character ->
                    CharacterCard(character, onClick)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(character: Character,
                  onClick: (Character) -> Unit) {
    val characterName = character.name
    Card(
        onClick = { onClick(character) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(175.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.md_theme_light_primaryContainer))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Load and display the image with CoilImage
            Image(
                painter = rememberImagePainter(data = character.image),
                null,
                Modifier
                    .clip(RectangleShape)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Title text overlaid on the image
            Text(
                text = characterName,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            )
        }
    }
}