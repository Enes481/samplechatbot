package org.enestigli.samplechatbot.presentation

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.enestigli.samplechatbot.data.Message
import org.enestigli.samplechatbot.util.Constants
import java.sql.Timestamp
import javax.inject.Inject

class ChatBotViewModel @Inject constructor() : ViewModel() {

    var index = mutableStateOf(value = 1)
    val isClickedBtn = mutableStateOf(false)

    fun customBotMessage(message: String, list: SnapshotStateList<Message>) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                list.add(
                    Message(
                        message,
                        Constants.RECEIVE_ID,
                        Timestamp(System.currentTimeMillis()).toString()
                    )
                )
            }
        }
    }

}