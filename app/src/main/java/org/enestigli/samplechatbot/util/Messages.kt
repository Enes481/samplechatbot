package org.enestigli.samplechatbot.util

import androidx.compose.runtime.mutableStateListOf

object Messages {

    const val key1 = "name"
    const val key2 = "email"
    const val key3 = "age"
    const val key4 = "place"
    const val key5 = "weight"
    const val key6 = "tall"

    const val Message1 = "Diyetkolik Ailesine Hoşgeldin.Sana yardımcı olabilmek için adını söyler misin ?"
    const val Message2 = "Bu harika, şimdi sana bildirim gönderebilmek için senin email ini öğrenebilir miyim ?"
    const val Message3 = "Email ini de aldığımıza göre, kaç yaşındasın söyler misin ?"
    const val Message4 = "çok az kaldı ! , Nerelisin ?"
    const val Message5 = "harika ! ,sana özel diyet programları çıkarabilmek için kilonu öğrenebilir miyim ?"
    const val Message6 = "Boyun kaç ?"
    const val Message7 = "Tüm işlemleri tamamladık teşekkürler bunlar sana yardım edebilmemiz için gerekliydi."


    val listOfMessages = mutableStateListOf(
        Message1,
        Message2,
        Message3,
        Message4,
        Message5,
        Message6,
        Message7)

    val listOfMessageKeys = mutableStateListOf(
        key1,
        key2,
        key3,
        key4,
        key5,
        key6)


}