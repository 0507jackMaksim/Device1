package ru.neura.fastrecycler


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Contacts
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintSet
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import 	androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import  ru.neura.fastrecycler.models.TweetMapper
import ru.neura.fastrecycler.models.Twitter

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val mAdapter = TwitterAdapter()


    override fun  onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter.attachDelegate(object: TwitterDelegate{
            override fun openProfile(username: String) {
                navigateToProfile(twitterName = username)
            }

            override fun openImage(url: String){
                navigateTImage(url = url)
            }
        })


        recyclerMain.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerMain.adapter = mAdapter

        populateData()
    }

    fun navigateToProfile(twitterName: String) {
        val intent = Intent(applicationContext, ProfileActivity::class.java)
        intent.putExtra("Username", twitterName )
        startActivity(intent)
    }

    fun navigateTImage(url: String){
        val intent = Intent(applicationContext, ImageActivity::class.java)
        intent.putExtra("Url", url)
        startActivity(intent)
    }
        fun populateData(){
            val tweets = Twitter.generateTweets()
            mAdapter.setData(data = tweets.map { TweetMapper().mapApiToUI(api = it) })
        }
    }







