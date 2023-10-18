package com.rickandmorty.app.screens.error

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rickandmorty.app.R

@Composable
fun ErrorDialog(
    navController: NavController,
    dialogText: String?
) {
    AlertDialog(
        text = {
            Text(text = dialogText?: stringResource(id = R.string.default_error))
        },
        onDismissRequest = {
            navController.popBackStack()
        },
        confirmButton = {
        },
        dismissButton = {
            TextButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "Dismiss")
            }
        }
    )
}
