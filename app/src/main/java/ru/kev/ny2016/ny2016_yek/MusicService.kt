package ru.kev.ny2016.ny2016_yek

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MusicService : Service() {

    private var mPlayer: MediaPlayer? = null

    //public fun Pause() {mPlayer!!.pause();}

    override fun onBind(intent: Intent): IBinder? {
        return null;
    }

    override fun onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.comin);
        mPlayer?.setOnCompletionListener {mPlayer?.start()};
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mPlayer!!.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer!!.stop()
    }
}
