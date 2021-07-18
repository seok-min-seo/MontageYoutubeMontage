package com.sukddaksoftware.montageyoutube2t.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sukddaksoftware.montageyoutube2t.Adapter.AdapterPlaylist

import com.sukddaksoftware.montageyoutube2t.Models.ModelPlaylist
import com.sukddaksoftware.montageyoutube2t.Models.PlaylistItems
import com.sukddaksoftware.montageyoutube2t.Network.YoutubeAPI
import com.sukddaksoftware.montageyoutube2t.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaylistFragment() : Fragment(R.layout.fragment_playlist) {

    lateinit var adapter : AdapterPlaylist
    lateinit var manager : LinearLayoutManager
    val videoList : MutableList<PlaylistItems> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_playlist, container, false)

        val rv : RecyclerView = view.findViewById(R.id.recycler_playlist)
        adapter = AdapterPlaylist(requireContext(), videoList)
        manager = LinearLayoutManager(context)

        rv.adapter = adapter
        rv.layoutManager = manager

        if (videoList.size == 0){
            getJson()
        }


        return view
    }

    private fun getJson() {
        val url : String = YoutubeAPI.BASE_URL + YoutubeAPI.playlist + YoutubeAPI.KEY + YoutubeAPI.part_playlist + YoutubeAPI.ChannelID
        var data : Call<ModelPlaylist>? = YoutubeAPI.getPlaylistVideo()?.getYT(url)
        if (data != null) {
            data.enqueue(object : Callback<ModelPlaylist> {
                override fun onResponse(call: Call<ModelPlaylist>, response: Response<ModelPlaylist>) {
                    if(response.errorBody() != null){
                        Log.v(ContentValues.TAG, "onResponse: " + response.body())
                    } else {
                        val mp : ModelPlaylist? = response.body()
                        mp?.items?.let { videoList.addAll(it) }
                        adapter.notifyDataSetChanged()
                    }
                }


                override fun onFailure(call: Call<ModelPlaylist>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ", t)
                }

            })
        }
    }
}