package org.enestigli.samplechatbot.presentation


import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.*
import org.enestigli.samplechatbot.R
import org.enestigli.samplechatbot.data.Message
import org.enestigli.samplechatbot.ui.theme.botcolor
import org.enestigli.samplechatbot.ui.theme.usercolor
import org.enestigli.samplechatbot.util.Constants.RECEIVE_ID
import org.enestigli.samplechatbot.util.Messages.Message1



@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FirstScreen(
    viewModel: ChatBotViewModel = hiltViewModel()
) {

    val list = remember { mutableStateListOf<Message>() }
    val botList = listOf("Peter", "Francesca", "Luigi", "Igor")
    val random = (0..3).random()
    val botName: String = botList[random]
    val hashMap: HashMap<String, String> = HashMap()
    val coroutineScope = rememberCoroutineScope()

    val listState = rememberLazyListState()

    viewModel.customBotMessage(message = Message1, list)

    Column(modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Image(
            painter = painterResource(id = R.drawable.diyetkolik_logo),
            contentDescription = "logo",
            modifier = Modifier.padding(10.dp)
        )
        Divider()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            ){
            items(list.size) { i ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                    if (list[i].id == RECEIVE_ID)
                        Arrangement.Start
                    else
                        Arrangement.End
                ) {
                    if (list[i].id == RECEIVE_ID) {
                        Item(
                            message = list[i],
                            botName,
                            botcolor,
                            list,
                            simpleTextFlag = true,
                            hashMap,
                            modifier = Modifier
                                .padding(
                                    start = 32.dp,
                                    end = 4.dp,
                                    top = 4.dp
                                ),
                        )
                    }
                    else {
                        Item(
                            message = list[i],
                            "user",
                            usercolor,
                            list,
                            simpleTextFlag = false,
                            hashMap,
                            Modifier.padding(
                                start = 4.dp,
                                end = 32.dp,
                                top = 4.dp
                            )
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun LazyListState.isAtBottom(): Boolean {

    return remember(this) {
        derivedStateOf {
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                val viewportHeight = layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount &&
                        lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
            }
        }
    }.value
}