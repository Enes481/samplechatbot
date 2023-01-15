package org.enestigli.samplechatbot.util

import androidx.compose.runtime.mutableStateListOf

object Messages {

    //kullanıcı ve bot mesajlarını key value şeklinde tuttuğumuz hashmap key'ler
    const val messageKey1 = "name"
    const val messageKey2 = "email"
    const val messageKey3 = "age"
    const val messageKey4 = "place"
    const val messageKey5 = "weight"
    const val messsageKey6 = "tall"

    //Hazır bot mesajları
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
        messageKey1,
        messageKey2,
        messageKey3,
        messageKey4,
        messageKey5,
        messsageKey6)
}