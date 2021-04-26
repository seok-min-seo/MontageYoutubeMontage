package com.sukddaksoftware.montageyoutube2t.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeThumbnailView
import com.squareup.picasso.Picasso
import com.sukddaksoftware.montageyoutube2t.Models.Item
import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.item_youtube.view.*


class YoutubeAdapter(
        val itemList : MutableList<Item>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var context: Context



    class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var thumbnailView : YouTubeThumbnailView
        lateinit var youtubeTitle: TextView
        lateinit var youtubeDescription:TextView

        init{

            thumbnailView = itemView.findViewById<View>(R.id.youtubeThumbnail) as YouTubeThumbnailView
            youtubeTitle = itemView.findViewById<View>(R.id.youtubeTitle) as TextView
            youtubeDescription = itemView.findViewById<View>(R.id.youtubeDescription) as TextView
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
       context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_youtube, parent, false)
        return YoutubeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = itemList[position]
        val thumbnailView = holder.itemView.youtubeThumbnail
        val youtubeTitle = holder.itemView.youtubeTitle
        val youtubeDescription = holder.itemView.youtubeDescription

        val Title = "${data.snippet.title}"
        val Description = "${data.snippet.description}"
        youtubeTitle.text = Title
        youtubeDescription.text = Description

        Picasso.get()
                .load("${data.snippet.thumbnails}")
                .into(thumbnailView)

        holder.itemView.setOnClickListener{
            Toast.makeText(context, Title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = itemList.size

}

