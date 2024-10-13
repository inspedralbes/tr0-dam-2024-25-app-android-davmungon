package com.example.fastquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun QuizScreen(onQuizEnd: (Int) -> Unit) {
    val preguntas = loadQuestionsFromJson()  // Cargamos las preguntas desde el JSON.
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }

    if (currentQuestionIndex >= preguntas.size) {
        onQuizEnd(score)  // Si hemos respondido todas las preguntas, pasamos a la pantalla de resultados.
    } else {
        val pregunta = preguntas[currentQuestionIndex]

        QuizQuestion(
            pregunta = pregunta,
            onAnswerSelected = { respuesta ->
                if (respuesta.correcta) {
                    score++  // Si la respuesta es correcta, sumamos un punto.
                }
                currentQuestionIndex++  // Pasamos a la siguiente pregunta.
            }
        )
    }
}

@Composable
fun QuizQuestion(pregunta: Pregunta, onAnswerSelected: (Resposta) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = pregunta.pregunta,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            painter = rememberImagePainter(pregunta.imatge),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Mostramos las opciones en botones en una sola columna
        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

        Column { // Cambiado a Column para los botones
            Button(onClick = { onAnswerSelected(pregunta.respostes[0]) }, modifier = buttonModifier) {
                Text(text = pregunta.respostes[0].resposta)
            }
            Button(onClick = { onAnswerSelected(pregunta.respostes[1]) }, modifier = buttonModifier) {
                Text(text = pregunta.respostes[1].resposta)
            }
            Button(onClick = { onAnswerSelected(pregunta.respostes[2]) }, modifier = buttonModifier) {
                Text(text = pregunta.respostes[2].resposta)
            }
            Button(onClick = { onAnswerSelected(pregunta.respostes[3]) }, modifier = buttonModifier) {
                Text(text = pregunta.respostes[3].resposta)
            }
        }
    }
}