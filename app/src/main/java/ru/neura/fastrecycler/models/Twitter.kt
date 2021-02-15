package ru.neura.fastrecycler.models

import kotlin.random.Random

class Twitter  {
    companion object {
        fun generateTweets(): List<TweetApi> {

            val tweets = ArrayList<TweetApi>()
            val  randomed = Random.nextInt(20, 40)

              for (i in 0..randomed) {
                  val type = Random.nextInt(0,3)
                  when (type) {
                    0 -> tweets.add(TweetApi(id = Random.nextInt(0, 50000), text = "This is the sample text",
                            username = "@Cyberpunk", displayName = "Cyber", avatar = "https://www.eventsforgamers.com/wp-content/uploads/2020/12/Cyberpunk-2077.jpg",
                            quote = null, image = "https://variety.com/wp-content/uploads/2020/12/Cyberpunk-2077.png"
                        )
                    )
                    1 -> tweets.add(TweetApi(id = Random.nextInt(0, 50000), text = "This is the sample text",
                            username = "Cyberpunk 2077", displayName = "Cyber", avatar = "https://www.eventsforgamers.com/wp-content/uploads/2020/12/Cyberpunk-2077.jpg",
                            quote = Quote(username = "@mrpunk", text = "My name is Bond... Mr.Bond"),
                            image = null))
                    else -> tweets.add(
                        TweetApi(
                            id = Random.nextInt(0, 50000), text = "This is the sample text",
                            username = "@Cyberpunk", displayName = "Cyber", avatar = "https://www.eventsforgamers.com/wp-content/uploads/2020/12/Cyberpunk-2077.jpg",
                            quote = null, image = null))
                  }
            }
            return tweets
         }
    }
}
