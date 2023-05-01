package com.commandiron.wheelpickercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.WheelPicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.commandiron.wheel_picker_compose.core.WheelTextPicker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    WheelPicker(
                        modifier = Modifier
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(
                                        topLeft = Offset.Zero,
                                        size = Size(width = 300.dp.toPx(), height = 44.dp.toPx()),
                                        brush = Brush.linearGradient(
                                            colors = listOf(Color.White, Color.White.copy(alpha = 0.0f)),
                                            start = Offset(0f, 10f),
                                            end = Offset(0f, 44.dp.toPx())

                                        )
                                    )
                                    drawRect(
                                        topLeft = Offset(0f, 176.dp.toPx()),
                                        size = Size(width = 300.dp.toPx(), height = 44.dp.toPx()),
                                        brush = Brush.linearGradient(
                                            colors = listOf(Color.White.copy(alpha = 0.0f), Color.White),
                                            start = Offset(0f, 176.dp.toPx()),
                                            end = Offset(0f, 215.dp.toPx())
                                        )
                                    )
                                }

                            },
                        count = 10,
                        size = DpSize(300.dp, 220.dp),
                        rowCount = 5,
                        startIndex = 3,
                        selectorProperties = WheelPickerDefaults.selectorProperties(
                            enabled = true,
                            color = Color(0xfff4f4f4),
                            shape = RectangleShape,
                            border = null,
                        ),
                        onScrollFinished = { snappedIndex ->
                            println("wheel picker snappedIndex: $snappedIndex")
                            null
                        }
                    ) { index, position ->
                        Box(
                            modifier = Modifier
                                .size(width = 300.dp, height = 44.dp)
                                .padding(10.dp)
                        ) {
                            println("index: $index, position: $position")
                            val style = if (position < 0.5) {
                                MaterialTheme.typography.titleLarge.copy(color = Color(0xff251c93))
                            } else {
                                MaterialTheme.typography.titleLarge.copy(color = Color.Black)
                            }
                            Text(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                text = "ELEMENT: $index",
                                style = style
                            )

                        }
                    }
                    WheelTextPicker(
                        rowCount = 5,
                        startIndex = 5,
                        onScrollFinished = { snappedIndex ->
                            println("text picker snappedIndex: $snappedIndex")
                            null
                        },
                        texts = listOf(
                            "ELEMENT: 0",
                            "ELEMEN: 1",
                            "ELEME: 2",
                            "ELEM: 3",
                            "ELE: 4",
                            "ELEM: 5",
                            "ELEME: 6",
                            "ELEMEN: 7",
                            "ELEMENT: 8",
                            "ELEMEN: 9",
                        )
                    )
                    WheelTimePicker { snappedTime ->
                        println(snappedTime)
                    }
                }
            }
        }
    }
}
