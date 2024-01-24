package com.example.truefalsecomposegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.truefalsecomposegame.ui.theme.TrueFalseComposeGameTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrueFalseComposeGameTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    val questions = listOf(
        "Question 1: Android is an operating system.",
        "Question 2: Kotlin is officially supported for Android development.",
        "Question 3: Constructors are not auto generated in Kotlin."
    )
    val answers = listOf(true, true, false)
    var questionIndex by remember {
        mutableStateOf(0)
    }

    var showWrongAnswer by remember { mutableStateOf(false) }
    var showCorrectAnswer by remember { mutableStateOf(false) }
    var showNextQuestion by remember { mutableStateOf(false) }
    var showAnswersRow by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${questions[questionIndex]}")

        if (showWrongAnswer) {
            AnswerResult(text = "False", color = Color.Red)
        }
        if (showCorrectAnswer) {
            AnswerResult(text = "True", color = Color.Green)
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    questionIndex++
                    showNextQuestion = true
                    showCorrectAnswer = false
                    showWrongAnswer = false
                    showAnswersRow = true
                }) {
                Text(text = "Next question")
            }
        }

        if (showAnswersRow)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        var isAnswerCorrect = true == answers[questionIndex]
                        if (isAnswerCorrect) {
                            showCorrectAnswer = true
                            showWrongAnswer = false
                            showNextQuestion = true
                            showAnswersRow = false
                        } else {
                            showWrongAnswer = true
                            showCorrectAnswer = false
                        }
                    }) {
                    Text(text = "True")
                }
                Spacer(modifier = Modifier.width(10.dp))

                Button(modifier = Modifier.weight(1f),
                    onClick = {
                        var isAnswerCorrect = false == answers[questionIndex]
                        if (isAnswerCorrect) {
                            showWrongAnswer = false
                            showCorrectAnswer = true
                            showNextQuestion = true
                            showAnswersRow = false
                        } else {
                            showCorrectAnswer = false
                            showWrongAnswer = true
                        }


                    }) {
                    Text(text = "False")
                }
            }
    }
}


@Composable
fun AnswerResult(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(color)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrueFalseComposeGameTheme {
        //Greeting("Android")
    }
}