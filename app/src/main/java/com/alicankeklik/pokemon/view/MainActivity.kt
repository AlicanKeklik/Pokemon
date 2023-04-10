package com.alicankeklik.pokemon.view

import android.content.Context
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.alicankeklik.pokemon.R


class MainActivity : AppCompatActivity() {
    private lateinit var testView : View
    private var windowManager1: WindowManager? = null
    private lateinit var mParams : WindowManager.LayoutParams
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        windowManager1 = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        testView = LayoutInflater.from(this).inflate(R.layout.overlay_screen, null)
        testView.visibility = View.GONE
        windowManager1 = this?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mParams = WindowManager.LayoutParams(
            400,
            400,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT)
        windowManager1!!.addView(testView, mParams)
    }
      fun gettestView(): View {


        return testView
    }

    override fun onDestroy() {
        super.onDestroy()
        windowManager1!!.removeView(testView)
        Log.e("Main","onDestroy")
    }
}