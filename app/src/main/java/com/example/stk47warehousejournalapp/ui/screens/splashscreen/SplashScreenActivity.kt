package com.example.stk47warehousejournalapp.ui.screens.splashscreen

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stk47warehousejournalapp.internal.Utils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(){

    private var mMediaPlayer : MediaPlayer? = null
    private var mCurrentVideoPosition : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.stk47warehousejournalapp.R.layout.activity_splash_screen)

        // Init UI
        initBackgroundVid()
        setupVideoListener()
    }

    override fun onPause() {
        super.onPause()
        mCurrentVideoPosition = mMediaPlayer!!.currentPosition
        backgroundVideoView.pause()
    }

    override fun onResume() {
        super.onResume()
        backgroundVideoView.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer!!.release()
        mMediaPlayer = null
    }

    private fun initBackgroundVid(){
        val videoUri = Utils.buildVideoUri(this)
        backgroundVideoView!!.setVideoURI(videoUri)
        backgroundVideoView!!.start()
    }

    private fun setupVideoListener(){
        backgroundVideoView.setOnPreparedListener {mediaPlayer ->  
            mMediaPlayer = mediaPlayer
            mMediaPlayer!!.isLooping = true
            if (mCurrentVideoPosition != 0){
                mMediaPlayer!!.seekTo(mCurrentVideoPosition!!)
                mMediaPlayer!!.start()
            }
        }
    }
}
