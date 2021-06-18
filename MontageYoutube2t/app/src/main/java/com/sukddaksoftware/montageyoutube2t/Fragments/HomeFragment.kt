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

import com.sukddaksoftware.montageyoutube2t.viewModel.MainViewModel
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





        return super.onCreateView(inflater, container, savedInstanceState)
    }
}