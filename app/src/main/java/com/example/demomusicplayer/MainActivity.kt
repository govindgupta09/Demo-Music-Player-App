package com.example.demomusicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.example.demomusicplayer.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.love_me_like_u_do_song)
        mediaPlayer.isLooping = true
        val totalTime = mediaPlayer.duration

        binding.imgPlay.setOnClickListener {
            mediaPlayer.start()
        }

        binding.imgPause.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.imgStop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        binding.seekBar.max = totalTime
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO()
            }
        })

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch (exception: Exception){
                    binding.seekBar.progress = 0
                }
            }
        }, 0)

    }
}