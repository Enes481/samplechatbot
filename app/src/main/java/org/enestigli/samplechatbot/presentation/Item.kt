package org.enestigli.samplechatbot.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.*
import org.enestigli.samplechatbot.R
import org.enestigli.samplechatbot.data.Message
import org.enestigli.samplechatbot.util.Constants
import org.enestigli.samplechatbot.util.Messages
import org.enestigli.samplechatbot.util.Messages.listOfMessages
import java.sql.Timestamp

@Composable
fun Item(
    message: Message,
    person: String,
    color: Color,
    list: SnapshotStateList<Message>,
    simpleTextFlag: Boolean,
    hashMap: HashMap<String, String>,
    modifier: Modifier,
    viewModel: ChatBotViewModel = hiltViewModel()
) {

    Column(verticalArrangement = Arrangement.Top) {
        Card(
            modifier = Modifier
                .padding(10.dp),
            backgroundColor = color,
            elevation = 10.dp
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(3.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        ) {
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
                                color = Color.White//Color(/*0xFF4552B8*/)
                            )
                        )
                        {
                            append(message.message)
                        }
                    }
                )
            }
        }
        if (simpleTextFlag && list.size != 13) {
            SimpleText(list = list, hashMap, viewModel)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SimpleText(
    list: SnapshotStateList<Message>,
    hashMap: HashMap<String, String>,
    viewModel: ChatBotViewModel,
) {

    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    var visible by remember { mutableStateOf(true) }

    var text by remember { mutableStateOf("") }
   // println(hashMap)

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInHorizontally(),
        exit = fadeOut() + slideOutHorizontally()
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.bringIntoViewRequester(bringIntoViewRequester)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(2.dp)
                        .onFocusEvent { focusState ->
                            if (focusState.isFocused) {
                                coroutineScope.launch {
                                    bringIntoViewRequester.bringIntoView()
                                }
                            }
                        },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    value = text,
                    onValueChange = { text = it },
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("send message") })
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        //Adds it to our local list
                        list.add(
                            Message(
                                text,
                                Constants.SEND_ID,
                                Timestamp(System.currentTimeMillis()).toString()
                            )
                        )
                        hashMap[Messages.listOfMessageKeys[viewModel.index.value - 1]] = text
                        viewModel.customBotMessage(listOfMessages[viewModel.index.value], list)
                        viewModel.index.value += 1
                        visible = false
                    }
                    text = ""
                }) {
                    Icon(
                        modifier = Modifier.padding(2.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_send_24),
                        contentDescription = "send message img"
                    )
                }
            }
            if (list.size == 3 || list.size == 5 || list.size == 7) {
                Button()
                if(viewModel.isClickedBtn.value){
                    visible = false
                    viewModel.isClickedBtn.value = false
                    list.add(
                        Message(
                            text,
                            Constants.SEND_ID,
                            Timestamp(System.currentTimeMillis()).toString()
                        )
                    )
                    hashMap[Messages.listOfMessageKeys[viewModel.index.value - 1]] = text
                    viewModel.customBotMessage(listOfMessages[viewModel.index.value], list)
                    viewModel.index.value += 1
                }
            }
        }
    }
}

@Composable
fun Button(
    viewModel: ChatBotViewModel = hiltViewModel()
) {

    Button(
        onClick = { viewModel.isClickedBtn.value = true },
    ) {
        Text("skip")
    }
}
