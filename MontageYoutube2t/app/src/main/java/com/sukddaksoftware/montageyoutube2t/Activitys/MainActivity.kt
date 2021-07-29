package com.sukddaksoftware.montageyoutube2t.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MotionEvent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : AppCompatActivity() {




    private fun Moving() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.TouchToScreen)
        val touchToScreen : Animation = AnimationUtils.loadAnimation(this,R.anim.kirakira)
        textView.startAnimation(touchToScreen)
        touchToScreen.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                textView.startAnimation(touchToScreen)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Moving()
        return super.onTouchEvent(event)
    }

    override fun onBackPressed() {
        moveTaskToBack(true);
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed()
    }

}


