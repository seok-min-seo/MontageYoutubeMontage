package com.sukddaksoftware.montageyoutube2t.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.sukddaksoftware.montageyoutube2t.ViewModel.MainViewModel
import com.sukddaksoftware.montageyoutube2t.Adapter.YoutubeAdapter
import com.sukddaksoftware.montageyoutube2t.Models.Item
import com.sukddaksoftware.montageyoutube2t.Models.Youtube
import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MainViewModel by viewModels()
    private val dataList : MutableList<Item> = mutableListOf()
    private lateinit var youtubeAdapter: YoutubeAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val youtubeAdapter = YoutubeAdapter(dataList)
        AndroidNetworking.initialize(context)
        AndroidNetworking.get("https://www.googleapis.com/youtube/v3")
                .build()
                .getAsObject(Youtube::class.java, object : ParsedRequestListener<Youtube>{
                    override fun onResponse(response: Youtube) {
                        dataList.addAll(response.items)
                        youtubeAdapter.notifyDataSetChanged()
                    }

                    override fun onError(anError: ANError?) {
                        TODO("Not yet implemented")
                    }



                })

        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = youtubeAdapter
        }
        viewModel.apply {
            itemLiveData.observe(viewLifecycleOwner, Observer {

            })

            loadingLiveData.observe(viewLifecycleOwner, Observer { isLoading ->
               // progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
            })
        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}