package com.sukddaksoftware.montageyoutube2t.Adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.sukddaksoftware.montageyoutube2t.Models.VideoYT
import com.sukddaksoftware.montageyoutube2t.R
import java.lang.Exception
import kotlin.coroutines.coroutineContext


class AdapterHome(
    var context: Context,
    var videoList : MutableList<VideoYT>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnailView : ImageView
        var youtubeTitle: TextView
        var youtubeDescription:TextView

        init{

            thumbnailView = itemView.findViewById<View>(R.id.youtubeThumbnail) as ImageView
            youtubeTitle = itemView.findViewById<View>(R.id.youtubeTitle) as TextView
            youtubeDescription = itemView.findViewById<View>(R.id.youtubeDescription) as TextView
        }

        fun setData(data: VideoYT)
        {

            val getJudul : String = data.Snippet.title
            val getTgl : String = data.Snippet.publishedAt
            val getThumb : String = data.Snippet.thumbnails.medium.url

            itemView.setOnClickListener {
            }

            youtubeTitle.setText(getJudul)
            youtubeDescription.setText(getTgl)
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

