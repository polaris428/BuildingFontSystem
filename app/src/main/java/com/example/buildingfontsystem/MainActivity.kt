@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.buildingfontsystem

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buildingfontsystem.ui.theme.BuildingFontSystemTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildingFontSystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val text = remember { mutableStateOf("플레이키보드") }
                    val letterSpacing = remember {  mutableStateOf("0") }
                    val spaceBetweenLines = remember { mutableStateOf("0") }
                    val textSize = remember { mutableStateOf("14") }
                    val selectedOption = remember { mutableStateOf("") }
                    Column(Modifier.padding(16.dp)) {
                        drawView(letterSpacing, spaceBetweenLines, text,textSize,selectedOption)

                    }
                }

            }

        }
    }
}
@Composable
fun GreetingPretendardvariable(name: String, letterSpacing: Int, spaceBetweenLines: Int,textSize: Int) {
    Column() {
        Text(
            text = "$name",
            fontSize=textSize.sp,
            letterSpacing = letterSpacing.sp,
            lineHeight = spaceBetweenLines.sp,
            fontFamily = FontFamily(Font(R.font.pretendardvariable))
        )
    }


}


@Composable
fun Greeting(name: String, letterSpacing: Int, spaceBetweenLines: Int,textSize: Int) {
    Column() {
        Text(
            text = "$name",
            fontSize=textSize.sp,
            letterSpacing = letterSpacing.sp,
            lineHeight = spaceBetweenLines.sp,
        )
    }


}

@ExperimentalMaterial3Api
@Composable
fun drawView(
    letterSpacing: MutableState<String>,
    spaceBetweenLines: MutableState<String>,
    text: MutableState<String>,
    textSize: MutableState<String>,
    selectedOption: MutableState<String>
) {
    if (letterSpacing.value != "" && spaceBetweenLines.value != "" && text.value != ""&& textSize.value!="")
        if (selectedOption.value=="시스템 폰트")
            Greeting(text.value, letterSpacing.value.toInt(), spaceBetweenLines.value.toInt(),textSize.value.toInt())
        else
            GreetingPretendardvariable(
                text.value, letterSpacing.value.toInt(), spaceBetweenLines.value.toInt(),textSize.value.toInt()
            )

    textFieldText("텍스트", text)
    textFieldNumber("자간", letterSpacing)
    textFieldNumber("행간", spaceBetweenLines)
    textFieldNumber("텍스트 사이즈", textSize)
    radioButtonSample(selectedOption)


}

@ExperimentalMaterial3Api
@Composable
fun textFieldNumber(label: String, amountInput: MutableState<String>) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = amountInput.value,
            onValueChange = {
                amountInput.value = it
            },
            label = { Text(text = label) },
            placeholder = { Text(text = "작성해주세요") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            )


        )
    }

}

@ExperimentalMaterial3Api
@Composable
fun textFieldText(label: String, amountInput: MutableState<String>) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = amountInput.value,
            onValueChange = {
                amountInput.value = it
            },
            label = { Text(text = label) },
            placeholder = { Text(text = "작성해주세요") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )


        )
    }
}
@Composable
fun radioButtonSample( selectedOption :MutableState<String> ) {
    val radioOptions = listOf("시스템 폰트", "프리텐다드")

    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption.value),
                        onClick = {
                            selectedOption.value=text
                        }
                    )

            ) {
                RadioButton(
                    selected = (text == selectedOption.value),
                    onClick = { selectedOption.value=text}
                )
                Text(
                    text = text
                )
            }
        }
    }
}



@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuildingFontSystemTheme {
        Column(Modifier.padding(16.dp)) {
            //var amountInput by remember { mutableStateOf("0") }
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val text = remember { mutableStateOf("플레이키보드") }
                val letterSpacing = remember { mutableStateOf("1") }
                val spaceBetweenLines = remember { mutableStateOf("20") }
                val textSize = remember { mutableStateOf("20") }
                Column(Modifier.padding(16.dp)) {

                }

            }

        }


    }
}