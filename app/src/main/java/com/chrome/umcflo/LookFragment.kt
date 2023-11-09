package com.chrome.umcflo

import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.chrome.umcflo.databinding.FragmentLockerBinding
import com.chrome.umcflo.databinding.FragmentLookBinding
import kotlin.math.abs

class LookFragment : Fragment() {

    lateinit var binding: FragmentLookBinding
    lateinit var songDB: SongDatabase

    lateinit var chartBtn : Button
    lateinit var videoBtn : Button
    lateinit var genreBtn : Button
    lateinit var situationBtn : Button
    lateinit var audioBtn : Button
    lateinit var atmostphereBtn : Button

    private lateinit var buttonList: List<Button>

    lateinit var chartTv : TextView
    lateinit var videoTv : TextView
    lateinit var genreTv : TextView
    lateinit var situationTv : TextView
    lateinit var audioTv : TextView
    lateinit var atmostphereTv : TextView

    private lateinit var textList: List<TextView>

    lateinit var scrollView : ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLookBinding.inflate(inflater, container, false)
        songDB = SongDatabase.getInstance(requireContext())!!

        // 스크롤 뷰 초기화
        scrollView = binding.lookSv

        // 버튼 초기화
        chartBtn = binding.lookChartBtn
        videoBtn =  binding.lookVideoBtn
        genreBtn =  binding.lookGenreBtn
        situationBtn =  binding.lookSituationBtn
        audioBtn =  binding.lookAudioBtn
        atmostphereBtn =  binding.lookAtmostphereBtn

        buttonList = listOf(chartBtn, videoBtn, genreBtn, situationBtn, audioBtn, atmostphereBtn)

        // 텍스트 초기화
        chartTv = binding.lookChartTv
        videoTv = binding.lookVideoTv
        genreTv = binding.lookGenreTv
        situationTv = binding.lookSituationTv
        audioTv = binding.lookAudioTv
        atmostphereTv = binding.lookAtmostphereTv

        textList = listOf(chartTv, videoTv, genreTv, situationTv, audioTv, atmostphereTv)

        chartBtn.setOnClickListener {
            initButton(0)
        }

        videoBtn.setOnClickListener {
            initButton(1)
        }

        genreBtn.setOnClickListener {
            initButton(2)
        }

        situationBtn.setOnClickListener {
            initButton(3)
        }

        audioBtn.setOnClickListener {
            initButton(4)
        }

        atmostphereBtn.setOnClickListener {
            initButton(5)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerview()
    }

    private fun initRecyclerview(){
        val recyclerView = binding.lookChartSongRv
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val lookAlbumRVAdapter = LockerAlbumRVAdapter()

        binding.lookChartSongRv.adapter = lookAlbumRVAdapter
        lookAlbumRVAdapter.addSongs(songDB.songDao().getSongs() as ArrayList<Song>)
    }

    private fun initButton(idx : Int) {
        for(presentBtn : Button in buttonList) {
            if(presentBtn == buttonList[idx]) {
                presentBtn.setBackgroundResource(R.drawable.selected_button)
            } else {
                presentBtn.setBackgroundResource(R.drawable.not_selected_button)
            }
        }
        scrollView.smoothScrollTo(0, textList[idx].top)
    }
}