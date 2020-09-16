package com.jqz.zhiqumusic.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jqz.zhiqumusic.R
import com.jqz.zhiqumusic.bean.PlayBean
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_play_video.*

/**
 * TODO,视频播放界面
 */
class PlayVideoActivity : AppCompatActivity() {
    var option : OrientationUtils ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        //播放音乐
        play()
    }

    private fun play() {
        val bean = intent.getSerializableExtra("play") as PlayBean
        //设置播放资源及视频名称
        videoPlay.setUp(bean.resource, true, bean.name)

        //增加封面
        val imageView: ImageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(this).load(bean.image).into(imageView)
        imageView.setImageResource(R.mipmap.album_icon_comment)
        videoPlay.thumbImageView = imageView

        //增加title
        videoPlay.titleTextView.visibility = View.VISIBLE
        //设置返回键
        videoPlay.backButton.visibility = View.VISIBLE
        //设置旋转
        option = OrientationUtils(this, videoPlay)

        //设置全屏按键功能
        videoPlay.fullscreenButton.setOnClickListener {
            option?.resolveByClick()
        }

        //是否可以滑动调整
        videoPlay.setIsTouchWiget(true)
        //关闭按钮
        videoPlay.backButton.setOnClickListener {
            onBackPressed()
        }
        videoPlay.startPlayLogic()


    }

    override fun onPause() {
        super.onPause()
        videoPlay.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlay.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        if (option != null) option?.releaseListener()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //先返回正常状态
        if (option?.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlay.fullscreenButton.performClick()
            return
        }
        //释放所有
        videoPlay.setVideoAllCallBack(null)
        super.onBackPressed()
    }

}
