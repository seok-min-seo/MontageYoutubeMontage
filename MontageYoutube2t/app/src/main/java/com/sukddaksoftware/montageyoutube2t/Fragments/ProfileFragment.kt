package com.sukddaksoftware.montageyoutube2t.Fragments

import android.content.ContentValues
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import com.sukddaksoftware.montageyoutube2t.Models.ChannelList
import com.sukddaksoftware.montageyoutube2t.Models.ModelChannel
import com.sukddaksoftware.montageyoutube2t.Models.ModelPlaylist
import com.sukddaksoftware.montageyoutube2t.Network.YoutubeAPI
import com.sukddaksoftware.montageyoutube2t.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ProfileFragment() : Fragment(R.layout.fragment_profile) {

    private lateinit var banner : ImageView
    private lateinit var logo : ImageView
    private lateinit var channelName : TextView
    private lateinit var subscriber : TextView
    private lateinit var decription : TextView
    private lateinit var videoCount : TextView
    private lateinit var viewsCount : TextView
    private lateinit var publishedAt : TextView
    private lateinit var loading1 : ShimmerFrameLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_profile,container,false)


        loading1 = view.findViewById(R.id.shimmerl)

        banner = view.findViewById(R.id.Banner)
        logo = view.findViewById(R.id.logo)
        channelName = view.findViewById(R.id.chnnel_name)
        subscriber = view.findViewById(R.id.subscriber_count)
        decription = view.findViewById(R.id.description)
        videoCount = view.findViewById(R.id.video_count)
        viewsCount = view.findViewById(R.id.views_count)
        publishedAt = view.findViewById(R.id.published_at)



        getJsonAPI()

        return view
    }

    private fun getJsonAPI() {
        loading1.visibility = View.VISIBLE
    val url : String = YoutubeAPI.BASE_URL + YoutubeAPI.CH + YoutubeAPI.KEY +
            YoutubeAPI.IDC + YoutubeAPI.CH_PART
        var data : Call<ModelChannel>? = YoutubeAPI.getChannelInfo()?.getYT(url)
        if (data != null) {
            data.enqueue(object : Callback<ModelChannel> {
                override fun onResponse(call: Call<ModelChannel>, response: Response<ModelChannel>) {
                    if(response.errorBody() != null){
                        Log.v(ContentValues.TAG, "onResponse: " + response.body())
                        loading1.visibility = View.GONE
                    } else {
                        if(response.body() != null) {
                            var mc : ModelChannel? = response.body()

                            mc?.items?.let { setData(it.get(0)) }
                            loading1.visibility = View.GONE

                        }
                    }
                }


                override fun onFailure(call: Call<ModelChannel>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ", t)
                    loading1.visibility = View.GONE
                    Toast.makeText(context,t.message,Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun setData(c: ChannelList) {
        channelName.text = c.snippet.title
        subscriber.text = "구독자 : ${c.statistic.subscriberCount} 명"
        decription.text = c.snippet.description
        videoCount.text = "영상 : ${c.statistic.videoCount} 개"
        viewsCount.text = "조회수 : ${c.statistic.viewCount} 회"
        val puB  = "${c.snippet.publishedAt}"
        val puBYear  :String = puB.substring(0,4)
        val puBMonth  :String= puB.substring(5,7)
        val pubDay :String = puB.substring(8,10)
        val puBHour  :String= puB.substring(11,13)
        val puBMinute : String = puB.substring(14,16)

        publishedAt.text = "몬타그는 ${puBYear}년 ${puBMonth}월 ${pubDay}일 ${puBHour}시 ${puBMinute}분에 첫 악마사냥을 시작했습니다."

        Picasso.get()
            .load(c.snippet.thumbnails.medium.url)
            .placeholder(R.drawable.ddd)
            .fit()
            .centerCrop()
            .into(logo, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    Log.d(ContentValues.TAG,"onSuccess: ")
                }

                override fun onError(e: Exception?) {
                    Log.e(ContentValues.TAG, "onError: ", e)
                }

            })
        Picasso.get()
            .load(c.channelBranding.imageBranding.bannerExternalUrl)
            .placeholder(R.drawable.ddd)
            .fit()
            .centerCrop()
            .into(banner, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    Log.d(ContentValues.TAG,"onSuccess: ")
                }

                override fun onError(e: Exception?) {
                    Log.e(ContentValues.TAG, "onError: ", e)
                }

            })
        logo.clipToOutline = true

    }

}