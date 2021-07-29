package com.sukddaksoftware.montageyoutube2t.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.youtube.player.YouTubePlayerView
import com.sukddaksoftware.montageyoutube2t.Adapter.AdapterHome
import com.sukddaksoftware.montageyoutube2t.Models.ModelHome
import com.sukddaksoftware.montageyoutube2t.Models.VideoYT
import com.sukddaksoftware.montageyoutube2t.Network.YoutubeAPI
import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.activity_youtube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment() : Fragment(R.layout.fragment_home) {

    private lateinit var adapterHome: AdapterHome
    private val videoList : MutableList<VideoYT> = mutableListOf()
    private lateinit var manager : LinearLayoutManager
    private lateinit var loading1 :ShimmerFrameLayout
    private lateinit var loading2 :ShimmerFrameLayout
    private var isScroll : Boolean = false
    private var currentItem : Int = 0
    private var totalItem : Int = 0
    private var scrollOutItem : Int = 0
    private var nextPageToken : String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        val rv : RecyclerView = view.findViewById(R.id.recyclerView)

        loading1 = view.findViewById(R.id.shimmerl)
        loading2 = view.findViewById(R.id.shimmerl2)

        adapterHome = AdapterHome(requireContext(),videoList)
        manager = GridLayoutManager(context,3)

        rv.adapter = adapterHome
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

        if(videoList.size == 0){
        getJson()
        }

        return view
    }

    fun getJson(){
        loading1.visibility = View.VISIBLE
        var url : String = YoutubeAPI.BASE_URL + YoutubeAPI.sch + YoutubeAPI.KEY + YoutubeAPI.ChannelID + YoutubeAPI.maxResults30 + YoutubeAPI.order + YoutubeAPI.part
        if( nextPageToken != ""){
            url = url + YoutubeAPI.NPT + nextPageToken
            loading1.visibility = View.GONE
            loading2.visibility = View.VISIBLE
        }
        val data : Call<ModelHome>? = YoutubeAPI.getHomeVideo()?.getYT(url)
        if (data != null) {
            data.enqueue(object : Callback<ModelHome> {
                override fun onResponse(call: Call<ModelHome>, response: Response<ModelHome>) {
                    if(response.errorBody() != null){
                        Log.v(TAG, "onResponse: " + response.body())
                        loading1.visibility = View.GONE
                        loading2.visibility = View.GONE
                        Toast.makeText(context, response.errorBody().toString(),Toast.LENGTH_SHORT).show()
                    } else {
                        val mh : ModelHome? = response.body()
                        mh?.items?.let { videoList.addAll(it) }
                        adapterHome.notifyDataSetChanged()
                        loading1.visibility = View.GONE
                        loading2.visibility = View.GONE
                        if(mh?.nextPageToken != null){
                            nextPageToken = mh.nextPageToken
                        }
                    }
                }

                override fun onFailure(call: Call<ModelHome>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                    loading1.visibility = View.GONE
                    loading2.visibility = View.GONE
                    Toast.makeText(context, t.message,Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}