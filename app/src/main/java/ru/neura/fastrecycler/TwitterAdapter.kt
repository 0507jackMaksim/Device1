package ru.neura.fastrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.neura.fastrecycler.models.Tweet
import ru.neura.fastrecycler.models.TweetSimple
import ru.neura.fastrecycler.models.TweetQuote
import ru.neura.fastrecycler.models.TweetImage
import com.squareup.picasso.Picasso

interface  TwitterDelegate{
    fun openProfile(username: String)
    fun openImage(url: String)
}


class TwitterAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    private val mDataList: MutableList<Tweet> = ArrayList()
    private var delegate: TwitterDelegate? = null

    fun attachDelegate(delegate: TwitterDelegate) {
        this.delegate = delegate
    }

    fun setData(data: List<Tweet>) {
        mDataList.clear()
        mDataList.addAll(data)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            1 -> ImageViewHolder(itemView = inflater.inflate(R.layout.cell_tweet_text_image, parent, false), delegate = delegate)
            2 -> QuoteViewHolder(itemView = inflater.inflate(R.layout.cell_tweet_text_quote, parent, false), delegate = delegate)
            else -> TextViewHolder(itemView = inflater.inflate(R.layout.cell_tweet_text, parent, false), delegate = delegate)

        }
    }

    override fun getItemCount(): Int{
        return mDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (mDataList[position]){
            is TweetSimple -> 0
            is TweetImage -> 1
            is TweetQuote -> 2

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bind(model = (mDataList[position] as TweetSimple))
            is QuoteViewHolder -> holder.bind(model = (mDataList[position] as TweetQuote))
            is ImageViewHolder -> holder.bind(model = (mDataList[position] as TweetImage))
        }
    }

    class TextViewHolder(itemView: View, val delegate: TwitterDelegate?): RecyclerView.ViewHolder(itemView){
        private val txtUsername: TextView = itemView.findViewById(R.id.txtTweetUsername)
        private val txtDisplayname: TextView = itemView.findViewById(R.id.txtTweetTweetname)
        private val txtText: TextView = itemView.findViewById(R.id.txtTweetText)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgTweetAvatar)

        fun bind(model: TweetSimple) {
            txtUsername.text = model.username
            txtDisplayname.text = model.displayName
            txtText.text = model.text

            txtUsername.setOnClickListener{
                delegate?.openProfile(username = model.username)
            }

            txtDisplayname.setOnClickListener{
                delegate?.openProfile(username = model.username)
            }

            imgAvatar.setOnClickListener {
                delegate?.openImage(url = model.avatar)
            }


            Picasso.get().load(model.avatar).into(imgAvatar)

        }
    }

    class QuoteViewHolder(itemView: View, val delegate: TwitterDelegate?): RecyclerView.ViewHolder(itemView){
        private val txtUsername: TextView = itemView.findViewById(R.id.txtTweetUsername)
        private val txtDisplayname: TextView = itemView.findViewById(R.id.txtTweetTweetname)
        private val txtText: TextView = itemView.findViewById(R.id.txtTweet)
        private val txtQuoteText: TextView = itemView.findViewById(R.id.txtTweetQuoteText)
        private val txtQuoteUsername: TextView = itemView.findViewById(R.id.txtTweetQuoteUsername)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgTweetAvatar)

        fun bind(model: TweetQuote){
            txtUsername.text = model.username
            txtDisplayname.text = model.displayName
            txtText.text = model.text
            txtQuoteText.text = model.quote.text
            txtQuoteUsername.text = model.quote.username

            txtUsername.setOnClickListener {
                delegate?.openProfile(username = model.username)
            }

            txtDisplayname.setOnClickListener{
                delegate?.openProfile(username = model.username)
            }

            imgAvatar.setOnClickListener{
                delegate?.openImage(url = model.avatar)
             }

            txtQuoteUsername.setOnClickListener{
                delegate?.openProfile(username = model.quote.username)
            }

            Picasso.get().load(model.avatar).into(imgAvatar)

        }
    }

    class ImageViewHolder(itemView: View, val delegate: TwitterDelegate?): RecyclerView.ViewHolder(itemView){
        private val txtUsername: TextView = itemView.findViewById(R.id.txtTweetUsername)
        private val txtDisplayname: TextView = itemView.findViewById(R.id.txtTweetTweetname)
        private val txtText: TextView = itemView.findViewById(R.id.txtTweet)
        private val  imgContent: ImageView= itemView.findViewById(R.id.imgTweetContent)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgTweetAvatar)

        fun bind(model: TweetImage){
            txtUsername.text = model.username
            txtDisplayname.text = model.displayName
            txtText.text = model.text

            txtUsername.setOnClickListener {
                delegate?.openProfile(username = model.username)
            }

            txtDisplayname.setOnClickListener {
                delegate?.openProfile(username = model.username)
            }

            imgAvatar.setOnClickListener {
                delegate?.openImage(url = model.avatar)
            }

            imgContent.setOnClickListener {
                delegate?.openImage(url = model.image)
            }

            Picasso.get().load(model.avatar).into(imgAvatar)
            Picasso.get().load(model.image).into(imgContent)

        }
    }
}
