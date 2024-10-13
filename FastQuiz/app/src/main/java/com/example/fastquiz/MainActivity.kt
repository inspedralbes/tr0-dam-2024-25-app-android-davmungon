package com.example.fastquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.fastquiz.ui.theme.FastQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FastQuizTheme {
                FastQuizApp()
            }
        }
    }
}

@Composable
fun FastQuizApp() {
    var screen by remember { mutableStateOf(Screen.Home) }
    var score by remember { mutableStateOf(0) }

    when (screen) {
        Screen.Home -> HomeScreen { screen = Screen.Quiz }
        Screen.Quiz -> QuizScreen(onQuizEnd = { finalScore ->
            score = finalScore
            screen = Screen.Result
        })
        Screen.Result -> ResultScreen(score = score) { screen = Screen.Home }
    }
}

enum class Screen {
    Home, Quiz, Result
}