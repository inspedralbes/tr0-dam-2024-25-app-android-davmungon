package com.example.fastquiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(score: Int, onRestartClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Respostes correctes: $score / 10",
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onRestartClick) {
                Text("Tornar a l'inici", fontSize = 24.sp)
            }
        }
    }
}