package mobi.eda.catchthetpu;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score =0;

    TextView txtView_score;
    TextView txtView_time;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    ImageView imageView7,imageView8,imageView9,imageView10,imageView11,imageView12;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ses effektleri
        final MediaPlayer bgSounder = MediaPlayer.create(MainActivity.this, R.raw.bg);
        bgSounder.start();
        */



        Intent intent = getIntent();
        String intentScore = intent.getStringExtra("score");
        int intScore = Integer.parseInt(intentScore);


        txtView_score = findViewById(R.id.txtView_score);
        txtView_time = findViewById(R.id.txtView_time);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);
        imageView12=findViewById(R.id.imageView12);

        imageArray = new ImageView[] {
                imageView1,  imageView2,
                imageView3,  imageView4,
                imageView5,  imageView6,
                imageView7,  imageView8,
                imageView9, imageView10,
                imageView11,imageView12
        };

        hideImages();

        txtView_score.setText("Score: "+score);
        txtView_time.setText("Time: "+ score);
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                txtView_time.setText("Time: " +  millisUntilFinished/1000);

            }
            @Override
            public void onFinish() {
                txtView_time.setText("STOP!🖐🏻");
                handler.removeCallbacks(runnable);
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);

                }

                Intent i;
                if (intScore==score || score >= intScore){
                    i = new Intent(MainActivity.this, Win.class);


                }else{
                    i = new Intent(MainActivity.this, Lose.class);
                }
                startActivity(i);
                finish();

            }
        }.start();
    }

    public void IncreaseScore(View view){

        final MediaPlayer winSound = MediaPlayer.create(this, R.raw.win1);  //ses efffekti
        winSound.start();
        score++;
        txtView_score.setText("Score: " + score);
    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(11);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}