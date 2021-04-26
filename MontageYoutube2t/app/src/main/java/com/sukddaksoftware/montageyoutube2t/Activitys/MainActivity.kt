package com.sukddaksoftware.montageyoutube2t.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MotionEvent

import com.sukddaksoftware.montageyoutube2t.R


class MainActivity : AppCompatActivity() {

    private fun Moving(){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Moving()
        return super.onTouchEvent(event)
    }

    }


