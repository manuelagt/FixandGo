package com.example.manue.fixandgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


public class EditRequest extends AppCompatActivity{

    EditText mTitle;
    EditText mDescription;
    EditText mName;
    EditText mEmail;

    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_request);

        mTitle = findViewById(R.id.edit_title);
        mDescription = findViewById(R.id.edit_description);
        mName = findViewById(R.id.edit_name);
        mEmail = findViewById(R.id.edit_email);

        //Initialize the local variables
        db = AppDatabase.getInstance(this);


        final Request requestEdit = getIntent().getParcelableExtra(MainActivity.EXTRA_REQUEST);
        mTitle.setText(requestEdit.getRequestTitle());
        mDescription.setText(requestEdit.getRequestDescription());
        mName.setText(requestEdit.getRequestName());
        mEmail.setText(requestEdit.getRequestEmail());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit_save_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString();
                String description = mDescription.getText().toString();
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();

                Request newRequest = new Request(title,description,name,email);

                if (!TextUtils.isEmpty(title)) {

                    requestEdit.setRequestTitle(title);
                    requestEdit.setRequestDescription(description);
                    requestEdit.setRequestName(name);
                    requestEdit.setRequestEmail(email);

                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent(EditRequest.this,MainActivity.class);
                    resultIntent.putExtra(MainActivity.EXTRA_REQUEST, requestEdit);
                    setResult(Activity.RESULT_OK, resultIntent);

                    finish();

                } else {
                    Snackbar.make(view, "Please, insert a title for the Game", Snackbar.LENGTH_LONG);
                }
            }
        });
    }

}
