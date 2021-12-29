package com.tenTwenty.testapp.ui.player


import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import com.tenTwenty.testapp.R
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView


private const val ARG_VIDEO_URL = "param1"


class VideoPlayerFragment : Fragment(){

    private var videoUrl: String? = null


    // private  var binding: FragmentVideoPlayerBinding? = null
    var imageIndex: Int = 0


    private var tvVideoTitle: TextView? = null
    private var ivBack: ImageView? = null




    lateinit var rewind: ImageView
    var progressBar: ProgressBar? = null
    var next: Button? = null
    var playerView: PlayerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoUrl = it.getString(ARG_VIDEO_URL)
            videoUrl ="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_video_player, container, false)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        progressBar = view.findViewById(R.id.progressBar)
        ivBack = view.findViewById(R.id.ivBack)
        tvVideoTitle = view.findViewById(R.id.tvVideoTitle)




        initVideoPlayer(view)

        playVideoFile(videoUrl!!)

        ivBack?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }


        return view
    }

    fun initVideoPlayer(view: View) {

        // currentVideo = getVideoByID(currentVideoId!!)


        playerView = view.findViewById(R.id.playerView)
        MediaPlayer.initialize(requireContext())
        MediaPlayer.exoPlayer?.preparePlayer(playerView!!, true)


        tvVideoTitle?.text = ""


        MediaPlayer.exoPlayer?.addListener(object : Player.EventListener {
            fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {

            }

            override fun onTracksChanged(
                trackGroups: TrackGroupArray,
                trackSelections: TrackSelectionArray
            ) {
            }

            override fun onLoadingChanged(isLoading: Boolean) {

                if (isLoading) {
                    progressBar?.setVisibility(View.VISIBLE)
                } else {
                    progressBar?.setVisibility(View.GONE)
                }
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    progressBar?.setVisibility(View.VISIBLE)

                } else {

                    progressBar?.setVisibility(View.GONE)
                }

                when (playbackState) {

                    Player.STATE_IDLE -> {

                    }
                    Player.STATE_BUFFERING -> {

                    }
                    Player.STATE_READY -> {

                    }
                    Player.STATE_ENDED -> {
                        requireActivity().supportFragmentManager.popBackStack()
                    }

                }


            }

            override fun onPlayerError(error: ExoPlaybackException) {

            }

            fun onPositionDiscontinuity() {

            }

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {

            }
        })

    }



    companion object {

        @JvmStatic
        fun newInstance(videoURL: String) =
            VideoPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VIDEO_URL, videoURL)


                }
            }
    }

    fun playVideoFile(url: String) {
        if (imageIndex == 0) {
//            rewind.isEnabled = false
//            rewind.isClickable = false
//            rewind.alpha = 0.5f
        } else {
            rewind.isEnabled = true
            rewind.isClickable = true
            rewind.alpha = 1.0f
        }
//        if (imageIndex == list.size - 1) {
//            next.isEnabled = false
//            next.isClickable = false
//            next.alpha = 0.5f
//        } else {
//            next.isEnabled = true
//            next.isClickable = true
//            next.alpha = 1.0f
//        }
//        var url: String = list.get(imageIndex).imageUrl.toString()

        context?.let {
            MediaPlayer.exoPlayer?.setSource(
                it,
                url
            )
        }
        var parama = PlaybackParameters(1f)
        MediaPlayer.exoPlayer?.setPlaybackParameters(parama)
        MediaPlayer.startPlayer()
    }

    override fun onPause() {
        MediaPlayer.exoPlayer?.pause()
        super.onPause()
    }

    override fun onDestroy() {
        if (MediaPlayer.exoPlayer != null) {
            MediaPlayer.exoPlayer?.release()

        }

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {

        }

        super.onDestroy()
    }

    override fun onDetach() {

        if (MediaPlayer.exoPlayer != null) {
            MediaPlayer.exoPlayer?.release()

        }
        super.onDetach()
    }











}


