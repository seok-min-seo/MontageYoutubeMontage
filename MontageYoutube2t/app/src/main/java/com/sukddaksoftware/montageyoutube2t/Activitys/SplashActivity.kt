package com.sukddaksoftware.montageyoutube2t.Activitys

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.sukddaksoftware.montageyoutube2t.R

import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.System.load


class SplashActivity : AppCompatActivity() {

    lateinit var soundpool:SoundPool
    var soundplay : Int = 0

    private fun Moving(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //사운드  초기화
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundpool = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .setMaxStreams(2)
            .build()
        soundplay = soundpool.load(this,R.raw.sound,1)
        //사운드 시작
        soundpool.setOnLoadCompleteListener { sounds, i, status ->
            sounds.play(soundplay,1.0f,1.0f,1,0,1.0f)
        }

        //애니메이션 실행
        animationView.playAnimation()

        animationView.addAnimatorUpdateListener {

            if (animationView.isAnimating) {
                Moving()
            }

        }

    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Moving()
        return super.onTouchEvent(event)
    }
}