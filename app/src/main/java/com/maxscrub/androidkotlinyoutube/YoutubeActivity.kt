package com.maxscrub.androidkotlinyoutube

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "jDzGcBpIge8"
const val YOUTUBE_PLAYLIST_ID = "PL3rP64NRtmbjJFGumln1bvmYHaqhxYvlt"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val TAG = "YouTubeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube);

        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600,180)
//        button1.text = "Button Added"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this);


    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: player is ${player?.javaClass}")
        Toast.makeText(this, "Initialized YoutubePlayer successfully", Toast.LENGTH_SHORT).show()

        player?.setPlayerStateChangeListener(playerStateChangeListener)
        player?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored) {
//            player?.cueVideo(YOUTUBE_VIDEO_ID)
            player?.loadVideo(YOUTUBE_VIDEO_ID)
        } else {
            player?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (result?.isUserRecoverableError == true) {
            result.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "error initializing player ($result)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Video is playing", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "Video stopped", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video is paused", Toast.LENGTH_SHORT).show()
        }

    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "onoz adz", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {

        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {

        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Video ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
            Toast.makeText(this@YoutubeActivity, "Error: $p0", Toast.LENGTH_SHORT).show()
        }

    }
}
