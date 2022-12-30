package mobi.eda.catchthetpu;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GoalScreen extends AppCompatActivity {

    Button button;
    EditText editText;
    String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_screen);

        button = findViewById(R.id.button);
        editText= findViewById(R.id.score);
        final MediaPlayer bgSound = MediaPlayer.create(this, R.raw.bg);
        bgSound.start();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = editText.getText().toString();
                if(editText.getText().toString().equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Lütfen Bir Değer Girin", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent i = new Intent(GoalScreen.this,MainActivity.class);
                    i.putExtra("score", score);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}