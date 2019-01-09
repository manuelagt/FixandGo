package com.example.manue.fixandgo;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.EditText;


public class AddRequest extends AppCompatActivity {


    EditText mTitle;
    EditText mDescription;
    EditText mName;
    EditText mEmail;


    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        mTitle = findViewById(R.id.add_title);
        mDescription = findViewById(R.id.add_description);
        mName = findViewById(R.id.add_name);
        mEmail = findViewById(R.id.add_email);

        //Initialize the local variables
        db = AppDatabase.getInstance(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_save_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRequest.this, MainActivity.class);
                Request request = new Request(mTitle.getText().toString(),
                        mDescription.getText().toString(),
                        mName.getText().toString(),
                        mEmail.getText().toString()
                );
                intent.putExtra(MainActivity.EXTRA_REQUEST, request);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
