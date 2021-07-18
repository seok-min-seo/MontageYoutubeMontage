package com.sukddaksoftware.montageyoutube2t.Adapter

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.sukddaksoftware.montageyoutube2t.Models.PlaylistItems
import com.sukddaksoftware.montageyoutube2t.R
import java.lang.Exception

class AdapterPlaylist(
    var context: Context,
    var videoList: List<PlaylistItems>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){



    class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnailView : ImageView
        var youtubeTitle: TextView
        var vid_count1 : TextView
        var vid_count2 : TextView

        init{

            thumbnailView = itemView.findViewById<View>(R.id.iv_playlist_thumb) as ImageView
            youtubeTitle = itemView.findViewById<View>(R.id.tv_playlist_title) as TextView
            vid_count1 = itemView.findViewById<View>(R.id.tv_video_count1) as TextView
            vid_count2 = itemView.findViewById<View>(R.id.tv_video_count2) as TextView

        }

        fun setData(data: PlaylistItems)
        {

            val getJudul : String = data.snippet.title
            val getCount : Int = data.contentDetails.itemCount
            val getThumb : String = data.snippet.thumnails.medium.url

            itemView.setOnClickListener {
            }

            youtubeTitle.setText(getJudul)
            vid_count1.setText("${getCount} 개의 영상")
            vid_count2.setText("${getCount}")
            Picasso.get()
                .load(getThumb)
                .placeholder(R.drawable.ddd)
                .fit()
                .centerInside()
                .into(thumbnailView, object : Callback {
                    override fun onSuccess() {
                        Log.d(ContentValues.TAG,"Thumbnail berhasil ditampilkan")
                    }

                    override fun onError(e: Exception?) {
                        Log.e(ContentValues.TAG, "Thumbnail error: ", e)
                    }

                })

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.row_item_playlist, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val videoYT : PlaylistItems = videoList.get(position)
        val yth : YoutubeViewHolder = holder as YoutubeViewHolder
        yth.setData(videoYT)
    }

    override fun getItemCount() = videoList.size

}
