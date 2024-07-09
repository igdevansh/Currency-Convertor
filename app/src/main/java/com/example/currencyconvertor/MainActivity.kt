package com.example.currencyconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconvertor.ui.theme.CurrencyConvertorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConvertorTheme {
                CC()
            }
        }
    }
}

@Composable
fun CC(){

    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Rupees") }
    var outputunit by remember { mutableStateOf("Rupees") }
    var iconversionfactor by remember{ mutableStateOf(1.0) }
    var oconversionfactor by remember{ mutableStateOf(1.0) }
    var iexpanded by remember{ mutableStateOf(false) }
    var oexpanded by remember{ mutableStateOf(false) }

    val customstyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 20.sp,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium
    )

    fun convert(){
        var inputamount = inputvalue.toDoubleOrNull() ?: 0.0
        outputvalue = ((inputamount)*(iconversionfactor/oconversionfactor)).toString()
    }


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Currency Convertor", style = customstyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange = {inputvalue = it
            convert()},
            label = {Text(text = "Enter the amount", style = customstyle)})

        Spacer(modifier = Modifier.height(14.dp))

        Row {
            //input button
            Box {
                Button(onClick = {iexpanded = true }) {
                    Text(text = "$inputunit")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded= false }) {
                    DropdownMenuItem(text = { Text(text = "Dollar", style = customstyle)}, onClick = { inputunit = "Dollar"
                        iconversionfactor = 83.46
                        convert()
                        iexpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Rupees", style = customstyle)}, onClick = { inputunit = "Rupees"
                        iconversionfactor = 1.0
                        convert()
                        iexpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Euro", style = customstyle)}, onClick = { inputunit = "Euro"
                        iconversionfactor = 90.33
                        convert()
                        iexpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Pound", style = customstyle)}, onClick = { inputunit = "Pound"
                        iconversionfactor = 106.90
                        convert()
                        iexpanded = false
                    })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //output button
            Box {
                Button(onClick = { oexpanded = true }) {
                    Text(text = "$outputunit")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded= false }) {
                    DropdownMenuItem(text = { Text(text = "Dollar", style = customstyle)}, onClick = { outputunit = "Dollar"
                        oconversionfactor = 83.46
                        convert()
                        oexpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Rupees", style = customstyle)}, onClick = { outputunit = "Rupees"
                        oconversionfactor = 1.0
                        convert()
                        oexpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Euro", style = customstyle)}, onClick = { outputunit = "Euro"
                        oconversionfactor = 90.33
                        convert()
                        oexpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Pound", style = customstyle)}, onClick = { outputunit = "Pound"
                        oconversionfactor = 106.90
                        convert()
                        oexpanded = false
                    })
                }


            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "RESULT --> $outputvalue $outputunit", style = customstyle)
    }
}

@Preview(showBackground = true)
@Composable
fun CCPreview() {
    CC()
}