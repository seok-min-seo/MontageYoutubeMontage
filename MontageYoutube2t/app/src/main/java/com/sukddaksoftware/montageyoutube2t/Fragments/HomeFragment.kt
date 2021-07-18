package com.sukddaksoftware.montageyoutube2t.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sukddaksoftware.montageyoutube2t.Adapter.AdapterHome
import com.sukddaksoftware.montageyoutube2t.Models.ModelHome
import com.sukddaksoftware.montageyoutube2t.Models.VideoYT
import com.sukddaksoftware.montageyoutube2t.Network.YoutubeAPI
import com.sukddaksoftware.montageyoutube2t.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeFragment() : Fragment(R.layout.fragment_home) {

    private lateinit var adapterHome: AdapterHome
    private val videoList : MutableList<VideoYT> = mutableListOf()
    private lateinit var manager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        val rv : RecyclerView = view.findViewById(R.id.recyclerView)
        adapterHome = AdapterHome(requireContext(),videoList)
        manager = GridLayoutManager(context,3)

        rv.adapter = adapterHome
        rv.layoutManager = manager

        if(videoList.size == 0){
        getJson()
        }

        return view
    }

    fun getJson(){
        val url : String = YoutubeAPI.BASE_URL + YoutubeAPI.sch + YoutubeAPI.KEY + YoutubeAPI.ChannelID + YoutubeAPI.maxResults30 + YoutubeAPI.order + YoutubeAPI.part

        var data : Call<ModelHome>? = YoutubeAPI.getHomeVideo()?.getYT(url)
        if (data != null) {
            data.enqueue(object : Callback<ModelHome> {
                override fun onResponse(call: Call<ModelHome>, response: Response<ModelHome>) {
                    if(response.errorBody() != null){
                        Log.v(TAG, "onResponse: " + response.body())
                    } else {
                        val mh : ModelHome? = response.body()
                        mh?.items?.let { videoList.addAll(it) }
                        adapterHome.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ModelHome>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                }

            })
        }
    }
}