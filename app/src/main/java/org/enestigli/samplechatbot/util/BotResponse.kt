package org.enestigli.samplechatbot.util

import androidx.compose.runtime.mutableStateListOf
import org.enestigli.samplechatbot.util.Constants.OPEN_GOOGLE
import org.enestigli.samplechatbot.util.Constants.OPEN_SEARCH
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {

    val list = mutableStateListOf("nerelisin ?","kaç yaşındasın ?","bla bla","test ", "amas")


    fun basicResponses(_message: String) : String {

        val random = (0..2).random()
        val message = _message.lowercase(Locale.getDefault())

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("enes") -> {
                when (random) {
                    0 -> "merhaba enes!"
                    1 -> "hoşgeldin enes"
                    2 -> "diyetkolik ailesine hoşgelgin enes!"
                    else -> "error" }
            }

            message.contains("enest5529@gmail.com") -> {
                when (random) {
                    0 -> "seni listeme kaydettim enes"
                    1 -> "teşekkürler mailini paylaştığın için enes"
                    2 -> "mailin doğru"
                    else -> "error" }
            }

            message.contains("balikesir") -> {
                when (random) {
                    0 -> "balıkesir çok güzel"
                    1 -> "bu harika"
                    2 -> "balıkesir bu harika bir şehir saol enes"
                    else -> "error" }
            }

            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I couldnt understand..."
                    1 -> "try again please"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
    }