package com.sukddaksoftware.montageyoutube2t.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
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
    private lateinit var loading1 : ShimmerFrameLayout
    private lateinit var loading2 : ShimmerFrameLayout
    private var isScroll : Boolean = false
    private var currentItem : Int = 0
    private var totalItem : Int = 0
    private var scrollOutItem : Int = 0
    private var nextPageToken : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_playlist, container, false)

        loading1 = view.findViewById(R.id.shimmerl)
        loading2 = view.findViewById(R.id.shimmerl2)

        val rv : RecyclerView = view.findViewById(R.id.recycler_playlist)
        adapter = AdapterPlaylist(requireContext(), videoList)
        manager = LinearLayoutManager(context)

        rv.adapter = adapter
        rv.layoutManager = manager

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollOutItem = manager.findFirstVisibleItemPosition()
                if ( isScroll && (currentItem + scrollOutItem == totalItem)){
                    isScroll = false
                    getJson()
                }
            }
        })

        if (videoList.size == 0){
            getJson()
        }


        return view
    }

    private fun getJson() {
        loading1.visibility = View.VISIBLE
        var url : String = YoutubeAPI.BASE_URL + YoutubeAPI.playlist + YoutubeAPI.KEY + YoutubeAPI.part_playlist + YoutubeAPI.ChannelID
        if( nextPageToken != ""){
            url = url + YoutubeAPI.NPT + nextPageToken
            loading1.visibility = View.GONE
            loading2.visibility = View.VISIBLE
        }
        val data : Call<ModelPlaylist>? = YoutubeAPI.getPlaylistVideo()?.getYT(url)
        if (data != null) {
            data.enqueue(object : Callback<ModelPlaylist> {
                override fun onResponse(call: Call<ModelPlaylist>, response: Response<ModelPlaylist>) {
                    if(response.errorBody() != null){
                        Log.v(ContentValues.TAG, "onResponse: " + response.body())
                        loading1.visibility = View.GONE
                        loading2.visibility = View.GONE
                    } else {
                        val mp : ModelPlaylist? = response.body()
                        mp?.items?.let { videoList.addAll(it) }
                        adapter.notifyDataSetChanged()
                        loading1.visibility = View.GONE
                        loading2.visibility = View.GONE
                        if(mp?.nextPageToken != null){
                            nextPageToken = mp.nextPageToken
                        }
                    }
                }


                override fun onFailure(call: Call<ModelPlaylist>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ", t)
                    loading1.visibility = View.GONE
                    loading2.visibility = View.GONE
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}