package com.sukddaksoftware.montageyoutube2t.Adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.sukddaksoftware.montageyoutube2t.Activitys.YoutubeActivity
import com.sukddaksoftware.montageyoutube2t.App.app
import com.sukddaksoftware.montageyoutube2t.Models.VideoYT
import com.sukddaksoftware.montageyoutube2t.R


class AdapterHome(
    var context: Context,
    var videoList : MutableList<VideoYT>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnailView : ImageView // 썸네일
        var youtubeTitle: TextView // 유튜브 제목
        var youtubePublishedAt:TextView // 유튜브 게시 시간

        init{


            thumbnailView = itemView.findViewById<View>(R.id.youtubeThumbnail) as ImageView
            youtubeTitle = itemView.findViewById<View>(R.id.youtubeTitle) as TextView
            youtubePublishedAt = itemView.findViewById<View>(R.id.youtubePublishedAt) as TextView
        }


        fun setData(data: VideoYT)
        {
            //API에서 정보들을 끌어옴
            val getJudul : String = data.Snippet.title
            val getTgl : String = data.Snippet.publishedAt
            val getThumb : String = data.Snippet.thumbnails.medium.url

            //영상을 클릭시 YoutubeActivity로 intent를 통해 이동하도록 설정
            itemView.setOnClickListener {
                var i = Intent(app.ApplicationContext(), YoutubeActivity::class.java)
                i.putExtra("video_id", data.id.videoId)
                i.putExtra("video_title", getJudul)
                i.addFlags(FLAG_ACTIVITY_NEW_TASK)
                app.ApplicationContext().startActivity(i)
            }

            youtubeTitle.setText(getJudul)
            youtubePublishedAt.setText(getTgl)
            Picasso.get()
                .load(getThumb)
                .placeholder(R.drawable.ddd)
                .fit()
                .centerCrop()
                .into(thumbnailView, object : Callback{
                    override fun onSuccess() {
                        Log.d(TAG,"Thumbnail berhasil ditampilkan")
                    }

                    override fun onError(e: Exception?) {
                        Log.e(TAG, "Thumbnail error: ", e)
                    }

                })

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
       context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_youtube, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val videoYT : VideoYT = videoList.get(position)
        val yth : YoutubeViewHolder = holder as YoutubeViewHolder
        yth.setData(videoYT)
    }

    override fun getItemCount() = videoList.size

}

