package ru.neura.fastrecycler.models

class TweetMapper {

    fun mapApiToUI(api: TweetApi): Tweet {
        return when {
            api.quote != null -> return TweetQuote(id = api.id, username = api.username, displayName = api.displayName,
                    text = api.text, quote = api.quote, avatar = api.avatar)
            api.image != null -> return TweetImage(id = api.id, username = api.username, displayName = api.displayName,
                    text = api.text, avatar = api.avatar, image = api.image)
            else -> TweetSimple(id = api.id, username = api.username, displayName = api.displayName,
                    text = api.text, avatar = api.avatar)
        }
    }
}