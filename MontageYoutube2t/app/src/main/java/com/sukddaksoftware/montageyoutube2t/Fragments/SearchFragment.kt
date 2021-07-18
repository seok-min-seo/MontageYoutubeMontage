package com.sukddaksoftware.montageyoutube2t.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

class SearchFragment(

) : Fragment(R.layout.fragment_search) {
    private lateinit var input_query : EditText
    private lateinit var btn_search : Button
    private lateinit var adapter: AdapterHome
    private lateinit var manager : LinearLayoutManager
    private var videoList : MutableList<VideoYT> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_search,container,false)

        input_query = view.findViewById(R.id.input_query)
        btn_search = view.findViewById(R.id.btn_search)
        val rv : RecyclerView = view.findViewById(R.id.recycler_search)
        adapter = AdapterHome(requireContext(), videoList)
        manager = LinearLayoutManager(context)

        rv.adapter = adapter
        rv.layoutManager = manager

        btn_search.setOnClickListener {
            if(!TextUtils.isEmpty(input_query.text.toString())){
                getJson(input_query.text.toString())
            } else{
                Toast.makeText(context, "글자를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun getJson(query: String) {
        val url: String =
            YoutubeAPI.BASE_URL + YoutubeAPI.sch + YoutubeAPI.KEY + YoutubeAPI.ChannelID +
                    YoutubeAPI.maxResults + YoutubeAPI.order + YoutubeAPI.part +
                    YoutubeAPI.query + query + YoutubeAPI.type
        YoutubeAPI.getHomeVideo()?.getYT(url)?.enqueue(object : Callback<ModelHome> {
            override fun onResponse(call: Call<ModelHome>, response: Response<ModelHome>) {
                if (response.errorBody() != null) {
                    Log.v(ContentValues.TAG, "onResponse search: " + response.errorBody().toString())
                } else {
                    val mh: ModelHome? = response.body()
                    if (mh?.items?.size != 0) {
                        mh?.let { videoList.addAll(it.items) }
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(context, "그런 영상은 없습니다.", Toast.LENGTH_SHORT).show()

                    }


                }
            }

            override fun onFailure(call: Call<ModelHome>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure search: ", t)
            }

        })

    }
}