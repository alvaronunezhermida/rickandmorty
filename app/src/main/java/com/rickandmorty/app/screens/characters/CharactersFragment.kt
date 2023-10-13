package com.rickandmorty.app.screens.characters

import android.view.LayoutInflater
import android.view.ViewGroup
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
import androidx.compose.material3.MaterialTheme
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
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.compose.rememberImagePainter
import com.rickandmorty.app.R
import com.rickandmorty.app.databinding.FragmentCharactersBinding
import com.rickandmorty.app.screens.BaseFragment
import com.rickandmorty.domain.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {

    override val viewModel: CharactersViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)

    override fun initObservers() {
        super.initObservers()
        launchWhenStarted { viewModel.charactersState.collect(::observeCharacters) }
    }

    private fun observeCharacters(characters: List<Character>) {
        binding.composeView.setContent {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            content = {
                items(characters) { character ->
                    CharacterCard(character, viewModel)
                }
            }
            )
        }
        binding.emptyState.root.isVisible = characters.isEmpty()
        binding.composeView.isVisible = characters.isNotEmpty()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(character: Character, viewModel: CharactersViewModel) {
    val characterName = character.name
    Card(
        onClick = { viewModel.onCharacterClicked(character) },
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