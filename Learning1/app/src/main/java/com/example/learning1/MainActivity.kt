package com.example.learning1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learning1.ui.theme.Learning1Theme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Learning1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    TipTime()
                }
            }
        }
    }
}

@Composable
fun TipTime(){

    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0

    var roundUp by remember { mutableStateOf(false) }

    var tipInput by remember { mutableStateOf("") }

    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0

    val tip = calculateTip(amount,tipPercent, roundUp)
    
    val focusManager = LocalFocusManager.current


    Column(modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)){
        
        Text(text = stringResource(R.string.calculate_tip),
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(label = R.string.bill_amount, value = amountInput,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions ( onNext = { focusManager.moveFocus(FocusDirection.Down) } ),
            onValueChange = { amountInput = it })
        EditNumberField(label = R.string.how_was_the_service,
            value = tipInput,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions( onDone = {focusManager.clearFocus()}),
            onValueChange = {tipInput = it})
        RoundTheTipRow(roundUp = roundUp, onRoundUpChanged = {roundUp = it})
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = stringResource(R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    value: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit){

    TextField(
        value = value,
        onValueChange = onValueChange ,
        label = { Text(stringResource(id = label)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions

    )
}

@Composable
fun RoundTheTipRow(roundUp: Boolean,
                   onRoundUpChanged: (Boolean) -> Unit,
                   modifier: Modifier = Modifier){
    Row(modifier = modifier
        .fillMaxWidth()
        .size(48.dp),
    verticalAlignment = Alignment.CenterVertically){
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),
            checked = roundUp, onCheckedChange = onRoundUpChanged)
    }
}

@VisibleForTesting
internal fun calculateTip(amount : Double , tipPercent : Double = 15.0,roundUp: Boolean) : String {
    var tip = tipPercent / 100 * amount
    if(roundUp)5
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Learning1Theme {
        TipTime()
    }
}