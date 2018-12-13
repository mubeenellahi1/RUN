package com.example.mobeen.run;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class BlogActivity extends AppCompatActivity {

    TextView contentTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        contentTV = findViewById(R.id.textView7);
        contentTV.setMovementMethod(new ScrollingMovementMethod());
    }
}
