package com.jqz.zhiqumusic.fragment.audio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jqz.zhiqumusic.R

/**
 * A simple [Fragment] subclass.
 * TODO,宝宝听中的精选界面
 */
class AudioChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_choice, container, false)
    }

}
