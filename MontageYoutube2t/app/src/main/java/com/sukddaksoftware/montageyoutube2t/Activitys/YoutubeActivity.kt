package com.sukddaksoftware.montageyoutube2t.Activitys

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : AppCompatActivity() {
    private lateinit var youTubePlayerView:YouTubePlayerView
    private lateinit var videoTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        youTubePlayerView = findViewById(R.id.youtubePlayerView)
        videoTitle = findViewById(R.id.VideoTitle)

        val data = intent
        val videoId = data.getStringExtra("video_id")
        val video_title = data.getStringExtra("video_title")

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId!!, 0f)
            }
        })

        VideoTitle.text = video_title

    }
}