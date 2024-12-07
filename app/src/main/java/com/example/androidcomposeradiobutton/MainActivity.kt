package com.example.androidcomposeradiobutton

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcomposeradiobutton.ui.theme.Brown
import com.example.androidcomposeradiobutton.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainFun()
        }
    }
}

@SuppressLint("NewApi")
@Preview(showBackground = true)
@Composable
fun MainFun() {
    val selectColor = remember { mutableStateOf(colors[0]) }
    val selectComplete = remember { mutableStateOf(Complete.entries.first()) }
    fun textPrice(): String {
        var buf = selectComplete.value.value
        var result: String = ""
        var i = 1
        while (buf != 0) {
            result = "${((buf % 10).toString())}$result"
            buf /= 10
            if (i%3==0){
                result = " $result"
            }
            i++
        }
        return result
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.size(250.dp)
        ) {
            Image(
                painter = getImage(selectColor.value),
                contentDescription = "preview",
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            )
        }
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,

            ) {
            colors.forEach {
                Box(
                    modifier = Modifier
                        .background(
                            Color(
                                red = it.red,
                                green = it.green,
                                blue = it.blue,
                                alpha = 0.5F
                            ),
                            shape = CircleShape
                        )
                ) {
                    RadioButton(
                        selected = (selectColor.value == it),
                        onClick = {
                            selectColor.value = it
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = it,
                            unselectedColor = it
                        ),
                        modifier = Modifier
                    )
                }
            }
        }
        Spacer(Modifier.padding(20.dp))
        Complete.entries.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                RadioButton(
                    selected = selectComplete.value == it,
                    onClick = {
                        selectComplete.value = it
                    }
                )
                Text(
                    text = it.name,
                    fontSize = 24.sp
                )
            }
        }
        Spacer(
            Modifier.padding(20.dp)
        )
        Text(
            "Стоимость: от ${textPrice()}",
            fontSize = 24.sp
        )
    }
}

val colors = arrayListOf(
    Color.Red,
    Color.Green,
    Color.Gray,
    Color.Yellow,
    Color.Blue,
    Brown,
    Purple40
)

enum class Complete(val value: Int) {
    Classic(2104900),
    Comfort(2174900),
    Luxe(2289900),
    Style(2329900)
}

@Composable
fun getImage(color: Color): Painter {
    when (color) {
        Color.Red -> return painterResource(R.drawable.car_red)
        Color.Green -> return painterResource(R.drawable.car_green)
        Color.Gray -> return painterResource(R.drawable.car_grey)
        Color.Yellow -> return painterResource(R.drawable.car_yellow)
        Color.Blue -> return painterResource(R.drawable.car_blue)
        Brown -> return painterResource(R.drawable.car_brown)
        Purple40 -> return painterResource(R.drawable.car_purple)
    }
    return painterResource(R.drawable.car_red)
}

class CompleteCar(
    val color: Color,
    val price: Complete,
)