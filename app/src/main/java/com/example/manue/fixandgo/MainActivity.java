package com.example.manue.fixandgo;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RequestAdapter.RequestClickListener{

    private List<Request> mRequests;
    private RequestAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mTextMessage;
    private MainViewModel mMainViewModel;

    public static final String EXTRA_REQUEST = "Request";
    public static final int REQUESTCODE1 = 1234;
    public static final int REQUESTCODE2 = 5678;
    private int mModifyPosition;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_fix:
                    mTextMessage.setText(R.string.title_requests);
                    return true;
                case R.id.navigation_professionals:
                    mTextMessage.setText(R.string.title_professionals);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the local variables
        mRecyclerView = findViewById(R.id.recyclerView);
        updateUI();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRequests = new ArrayList<>();

        mMainViewModel = new MainViewModel(getApplicationContext());

        mMainViewModel.getRequests().observe(this, new Observer<List<Request>>() {
            @Override
            public void onChanged(@Nullable List<Request> requests) {
                mRequests = requests;
                updateUI();
            }
        });





        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        mMainViewModel.delete(mRequests.get(position));

                    }


                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public void goToAddPage(View view) {
        startActivityForResult(new Intent(MainActivity.this, AddRequest.class), REQUESTCODE1);

    }



    private void updateUI() {
//        mGames = db.reminderDao().getAllGames();
        if (mAdapter == null) {
            mAdapter = new RequestAdapter(mRequests, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapList(mRequests);
        }

    }

    @Override
    public void reminderOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, EditRequest.class);
        mModifyPosition = i;

        intent.putExtra(EXTRA_REQUEST, mRequests.get(i));
        startActivityForResult(intent, REQUESTCODE2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUESTCODE1){
            if (resultCode == RESULT_OK){
                Request addedRequest = data.getParcelableExtra(EXTRA_REQUEST);
                mRequests.add(addedRequest);
                mMainViewModel.insert(addedRequest);
            }
        } else if (requestCode == REQUESTCODE2){
            if(resultCode == RESULT_OK){
                Request editedRequest = data.getParcelableExtra(EXTRA_REQUEST);
                mRequests.set(mModifyPosition, editedRequest);
                mMainViewModel.update(editedRequest);
            }
        }
    }

}
