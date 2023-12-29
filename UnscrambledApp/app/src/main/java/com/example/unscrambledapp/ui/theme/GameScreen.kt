package com.example.unscrambledapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.graphics.BlendModeColorFilterCompat
import com.example.unscrambledapp.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.lang.reflect.Modifier

@Composable
fun GameStatus(wordCount: Int,score: Int,modifier: Modifier = Modifier){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .size(48.dp)
        ) {
            Text(
                text = stringResource(R.string.word_count, wordCount),
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                text = stringResource(id = R.string.score, score),
                fontSize = 18.sp
            )

        }

}

@Composable
fun GameLayout(userGuess: String,
               isGuessWrong: Boolean,
               onUserGuessChanged: (String) -> Unit,
               onKeyboardDone: () -> Unit,
               modifier: Modifier = Modifier,
               currentScrambledWord: String){

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        Text(text = currentScrambledWord,
        fontSize = 45.sp,
        modifier = modifier.align(Alignment.CenterHorizontally))

        Text(text = stringResource(R.string.instructions),
            fontSize = 17.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally))

        OutlinedTextField(value = userGuess
            , onValueChange = onUserGuessChanged,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {
                if(!isGuessWrong){
                    Text(text = stringResource(R.string.enter_your_word))
                }else{
                    Text(text = stringResource(R.string.wrong_guess))
                }},
        isError = isGuessWrong,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        )
        )
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier,
               gameViewModel: GameViewModel = viewModel()){

    val gameUiState by gameViewModel.uiState.collectAsState()
    
    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)){
        GameStatus(gameUiState.currentWordCount,gameUiState.score)

        GameLayout(userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            currentScrambledWord = gameUiState.currentScrambledWord)

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            OutlinedButton(onClick = { gameViewModel.skipWord() },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)){
                Text(text = stringResource(id = R.string.skip))
            }
            Button(modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp),
                onClick = { gameViewModel.checkUserGuess() }
            ){
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
    
    if(gameUiState.isGameOver){
        FinalScoreDialog(score = gameUiState.score, onPlayAgain = {gameViewModel.resetGame()})
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain:() -> Unit,
    modifier: Modifier = Modifier
){
    val activity = (LocalContext.current as Activity)

    AlertDialog(onDismissRequest = {},
    title = { Text(text = stringResource(R.string.congratulations))},
    text = { Text(text = stringResource(id = R.string.you_scored, score))},
    modifier = modifier,
    dismissButton = {
        TextButton(
            onClick = {
                activity.finish()
            })
        {
            Text(text = stringResource(id = R.string.exit))
        }
    },
    confirmButton = {
        TextButton(onClick = {
            onPlayAgain()
        }
        ) {
            Text(stringResource(id = R.string.play_again))
        }
    })

}