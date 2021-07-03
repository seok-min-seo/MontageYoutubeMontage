package com.sukddaksoftware.montageyoutube2t.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sukddaksoftware.montageyoutube2t.viewModel.MainViewModel
import com.sukddaksoftware.montageyoutube2t.Adapter.YoutubeAdapter
import com.sukddaksoftware.montageyoutube2t.Models.VideoYT
import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment(private var adapter: YoutubeAdapter ,
                   private val videoList : MutableList<VideoYT> = mutableListOf(),
                   private var manager : LinearLayoutManager
                   ) : Fragment(R.layout.fragment_home) {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_home, container, false)
        var rv : RecyclerView = view.findViewById(R.id.recyclerView)

        adapter = YoutubeAdapter(requireContext(),videoList)
        manager = LinearLayoutManager(requireContext())

        rv.adapter = adapter
        rv.layoutManager = manager

        if(videoList.size == 0){
        getJson()
        }

        return view
    }

    fun getJson(){

    }
}