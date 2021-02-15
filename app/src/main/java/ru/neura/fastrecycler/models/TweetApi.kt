package ru.neura.fastrecycler.models

data class TweetApi(val id: Int, val username: String, val displayName: String ,val quote: Quote?, val image: String?,
                    val avatar: String, val text: String)
data class Quote(val username: String, val text: String)

sealed class Tweet
data class TweetSimple(val id: Int, val username: String, val displayName:  String, val avatar: String, val text: String, val viewType: Int = 0): Tweet()
data class TweetQuote(val id: Int, val username: String, val displayName: String, val avatar: String, val text: String, val quote: Quote, val viewType: Int = 1 ): Tweet()
data class TweetImage(val id: Int, val username: String, val displayName: String, val avatar: String, val text: String, val image: String, val viewType: Int = 2): Tweet()
