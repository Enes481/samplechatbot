package org.enestigli.samplechatbot.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import org.enestigli.samplechatbot.R
import org.enestigli.samplechatbot.data.Message
import org.enestigli.samplechatbot.ui.theme.botcolor
import org.enestigli.samplechatbot.ui.theme.usercolor
import org.enestigli.samplechatbot.util.BotResponse
import org.enestigli.samplechatbot.util.Constants.RECEIVE_ID
import org.enestigli.samplechatbot.util.Constants.SEND_ID
import org.enestigli.samplechatbot.util.Messages.Message1
import org.enestigli.samplechatbot.util.Messages.listOfMessageKeys
import org.enestigli.samplechatbot.util.Messages.listOfMessages
import java.sql.Timestamp

var index = 1

@Composable
fun FirstScreen() {

    val list = remember { mutableStateListOf<Message>() }

    val botList = listOf("Peter", "Francesca", "Luigi", "Igor")
    val random = (0..3).random()
    val botName: String = botList[random]

    val hashMap: HashMap<String, String> = HashMap<String, String>()

    customBotMessage(message = Message1, list)



    Column(
        modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.diyetkolik_logo),
            contentDescription = "logo",
            modifier = Modifier.padding(10.dp))
        Divider()
        LazyColumn{
            items(list.size) { i ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                    if (list[i].id == RECEIVE_ID)
                        Arrangement.Start
                    else
                        Arrangement.End
                ){
                    if (list[i].id == RECEIVE_ID)
                        Item(message = list[i], botName, botcolor)
                    else
                        Item(message = list[i], "user", usercolor)
                }
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        SimpleOutlinedTextFieldSample(list, hashMap)
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SimpleOutlinedTextFieldSample(
    list: SnapshotStateList<Message>,
    hashMap: HashMap<String, String>
) {

    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    var text by remember { mutableStateOf("") }


    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .onFocusEvent { focusState ->
                        if (focusState.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                value = text,
                onValueChange = { text = it },
                label = { Text("send message") })
            IconButton(onClick = {
                if (text.isNotEmpty()) {
                    //Adds it to our local list
                    list.add(
                        Message(
                            text,
                            SEND_ID,
                            Timestamp(System.currentTimeMillis()).toString()
                        )
                    )
                    hashMap.put(listOfMessageKeys[index - 1], text)
                    customBotMessage(listOfMessages[index], list)

                    index += 1
                    println(hashMap)
                    //list.add(Message(response, RECEIVE_ID, timeStamp))
                    //botResponse(text,list)
                }
                text = ""
            }) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_send_24),
                    contentDescription = "send message img"
                )
            }
        }
    }
}

private fun customBotMessage(message: String, list: SnapshotStateList<Message>) {

    GlobalScope.launch {
        delay(1000)
        withContext(Dispatchers.Main) {
            list.add(
                Message(
                    message,
                    RECEIVE_ID,
                    Timestamp(System.currentTimeMillis()).toString()
                )
            )
        }
    }
}

private fun botResponse(message: String, list: SnapshotStateList<Message>) {


    val timeStamp = Timestamp(System.currentTimeMillis()).toString()
    GlobalScope.launch {
        //Fake response delay
        delay(1000)

        withContext(Dispatchers.Main) {
            //Gets the response
            val response = BotResponse.basicResponses(message)
            // val response = "${list2[index]} $username"
            //Adds it to our local list
            index += 1
            println("mesaj" + listOfMessages[index])
            //customBotMessage(listOfMessages[index],list)

            list.add(Message(response, RECEIVE_ID, timeStamp))

        }
    }
}


