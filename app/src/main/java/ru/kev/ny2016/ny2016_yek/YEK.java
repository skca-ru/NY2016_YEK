package ru.kev.ny2016.ny2016_yek;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class YEK extends AppCompatActivity {

    private int aaDuration;
    SharedPreferences sPref;
    //    protected static String[] Names;
    protected int CurrentImage;
    //protected MediaPlayer mediaPlayer;
    protected ImageView IV;
    private GestureDetector gestureDetector;
    protected boolean SlideShowMode ;
    protected Timer timer;
    protected Handler hSlideShow;
    protected ComponentName PlayMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        SlideShowMode = false;

        setContentView(R.layout.activity_yek);
        //      String[] Names = new String[3];
        //      Names[0] = "vay";
        CurrentImage = 1;
        IV = (ImageView) findViewById(R.id.imageView);

        //жесты
        View MainFrame =(View) findViewById(R.id.MainFrame);

        gestureDetector = initGestureDetector();

        IV.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        //ViewSwitcher VS = (ViewSwitcher) findViewById(R.id.fullscreen_content_controls);
        //VS.on
        //MenuItem miMusic = (MenuItem) findViewById(R.id.PlayStop);
        //miMusic.setOnMenuItemClickListener(this.OnMusickClick((View)miMusic));

        hSlideShow = new Handler(){public void handleMessage(android.os.Message msg){_NextImage();}};
        sPref = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        aaDuration = Integer.parseInt(sPref.getString("aaDuration","500"));

        //музыка
        //       mediaPlayer.setDataSource(DATA_STREAM);
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //Log.d(LOG_TAG, "prepareAsync");
        //mediaPlayer.setOnPreparedListener(this);
        //mediaPlayer.prepareAsync();
        //mediaPlayer = MediaPlayer.create(this, R.raw.comin);
        //mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        //    public void onCompletion(MediaPlayer arg0) {
        //        arg0.start();//Запускаем воспроизведение заново
        //    }});

        Boolean bStartMusicPlay = sPref.getBoolean("PlayMusic",false);
        //boolean bStartMusicPlay = true;
        if (bStartMusicPlay) {
            //mediaPlayer.start();
            PlayMusic = startService(new Intent(this, MusicService.class));
        };
    }

    @Override
    protected void onResume()
    {   super.onResume();
        aaDuration =  Integer.parseInt(sPref.getString("aaDuration","500"));
    }

    @Override
    public void onPause(){
        if (PlayMusic!= null) {
            /*PlayMusic.Pause();*/};
        super.onPause();
    }

    @Override
    public void onDestroy() {

     //   mediaPlayer.release();
        stopService(new Intent(this, MusicService.class));
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mainmenu, menu);
//        if (this.mediaPlayer.isPlaying()) {
//            MenuItem menuMusicPlay = menu.findItem(R.id.Menu_MusicPlay);
//            menuMusicPlay.setTitle(getString(R.string.Menu_MusicStop));
//        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.Menu_MusicPlay:
                MusicClick(item);
                break;
            case R.id.Menu_SlideShow:
                SlideShow();
                item.setChecked(!item.isChecked());
                break;
            case R.id.Menu_About:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.Menu_Custom:
                Intent intentCustom = new Intent(this, SettingsActivity.class);
                startActivity(intentCustom);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //public void _NextButtonClick {}
    public void _NextButtonClick(View view) {
        _NextImage();
    }
    public void _PrevButtonClick(View view) {_NextImage(false);}

    public void _NextImage(){ShowNextImage(true);}
    public void _NextImage(boolean Forward){ShowNextImage(Forward);}
    private void ShowNextImage(boolean Forward){
        int CountImages;
        CountImages = 8;
        try {
            //region АнимацияПерехода
            Animation fadeOut = new AlphaAnimation(0,1);
            fadeOut.setInterpolator(new AccelerateInterpolator()); // add this
            fadeOut.setDuration(aaDuration);
           // fadeOut.setStartOffset(aaDuration);
            AnimationSet animationFO = new AnimationSet(false); // change to false
            animationFO.addAnimation(fadeOut);
            //IV.startAnimation(animationFO);

            if (Forward!=true) {CurrentImage = CurrentImage -2; if (CurrentImage<=0) {CurrentImage = CountImages-1;};};
            switch (CurrentImage) {
                case 1:
                    IV.setImageResource(R.drawable.vay);
                    break;
                case 2:
                    IV.setImageResource(R.drawable.greentree);
                    break;
                case 3:
                    IV.setImageResource(R.drawable.park1);
                    break;
                case 4:
                    IV.setImageResource(R.drawable.park2);
                    break;
                case 5:
                    IV.setImageResource(R.drawable.park3);
                    break;
                case 6:
                    IV.setImageResource(R.drawable.park4);
                    break;
                case 7:
                    IV.setImageResource(R.drawable.summer2019);
                    break;
                case 8:
                    IV.setImageResource(R.drawable.venik);
                    CurrentImage = 0;}




                    Animation fadeIn = new AlphaAnimation(0,1);
                    fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
                    fadeIn.setDuration(aaDuration);
//
////                    Animation fadeOut = new AlphaAnimation(1,0);
////                    fadeOut.setInterpolator(new AccelerateInterpolator()); // add this
////                    fadeOut.setDuration(aaDuration);
//                    //fadeOut.setStartOffset(aaDuration);
//
                    AnimationSet animation = new AnimationSet(false); // change to false
                    animation.addAnimation(fadeIn);
                    //animation.addAnimation(fadeOut);
                    animation.setRepeatCount(1);
                    //IV.setAnimation();
                    IV.startAnimation(animation);
                    //endregion

                    CurrentImage++;

        }
            finally {};

        };

   public  void  SlideShow(){

        SlideShowMode = !SlideShowMode;
        if (SlideShowMode) {
            timer = new  Timer();
           // timer.scheduleAtFixedRate(new TimerTask() {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run(){hSlideShow.sendEmptyMessage(CurrentImage);}}, 500,1000);}
            else {if (timer != null) {
                timer.cancel();
                timer = null;};

    }}

   public void MusicClick(MenuItem item) {
        //MenuItem item;
        //item = (MenuItem) view;
//        if (this.mediaPlayer.isPlaying()) {
//            this.mediaPlayer.pause();
//            item.setTitle(getString(R.string.Menu_MusicPlay));
//        } else {
//            this.mediaPlayer.start();
//            item.setTitle(getString(R.string.Menu_MusicStop));
//        }
//        ;
       if (PlayMusic == null) {
           PlayMusic = startService(new Intent(this, MusicService.class));}
       else {
           stopService(new Intent(this, MusicService.class)); PlayMusic = null;}
       ;
   }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(this.getBaseContext(),new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        return false;
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        showToast("Up Swipe");
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        showToast("Left Swipe");
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        showToast("Right Swipe");
                    }
                } catch (Exception e) {} //for now, ignore
                return false;
            }

            private void showToast(String phrase){
                Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//    class SlideShowTimerTask extends TimerTask {
//   @Override
//    public void run(){
//       this.runOnUiThread(new Runnable(){
//           @Override
//           public void run(){}
//       })
//    }
//    }
//}