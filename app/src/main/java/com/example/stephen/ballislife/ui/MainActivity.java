package com.example.stephen.ballislife.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.stephen.ballislife.R;


public class MainActivity extends AppCompatActivity {
    private Button mViewTeamsButton;
    private EditText mTeamEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTeamEditText = (EditText) findViewById(R.id.teamEditText);
        mViewTeamsButton = (Button) findViewById(R.id.viewTeamsButton);

        mViewTeamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String team = mTeamEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, TeamsActivity.class);
                intent.putExtra("team", team);
                startActivity(intent);
            }
        });
    }
}