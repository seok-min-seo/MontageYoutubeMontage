package com.sukddaksoftware.montageyoutube2t.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukddaksoftware.montageyoutube2t.Models.Item
import com.sukddaksoftware.montageyoutube2t.Repository.YoutubeService
import kotlinx.coroutines.launch


//데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MainViewModel constructor(
    private val service : YoutubeService,

): ViewModel(){
    //뮤터블 라이브 데이터 - 수정 가능한 녀석
    // 라이브데이터 - 값 변동 안됨

    //내부에서 설정하는 자료형은 뮤터블로 변경가능하도록 설정정

   val itemLiveData = MutableLiveData<List<Item>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    init{
        fetchYoutubeInfo()
    }

    fun fetchYoutubeInfo(){
        //로딩 시작
        loadingLiveData.value = true

        viewModelScope.launch {



            //로딩 끝
            loadingLiveData.value = false
        }


        }

}