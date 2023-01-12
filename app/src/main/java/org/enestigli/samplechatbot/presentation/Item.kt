package org.enestigli.samplechatbot.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.enestigli.samplechatbot.data.Message

@Composable
fun Item(
    message: Message,
    person: String,
    color: Color
) {


    Card(
        modifier = Modifier
            .padding(10.dp),
        backgroundColor = color,
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(10.dp)
                .height(IntrinsicSize.Max)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Medium,
                            color = Color.Black)) {
                            append("$person: ")
                        }
                    },
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W900,
                                color = Color(0xFF4552B8)
                            )
                        )
                        {
                            append(message.message)
                        }
                    }
                )
            }
        }
    }
}
