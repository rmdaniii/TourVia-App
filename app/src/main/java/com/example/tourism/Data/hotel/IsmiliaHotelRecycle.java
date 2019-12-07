package com.example.tourism.Data.hotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourism.Data.DataModel;
import com.example.tourism.Data.FirebaseViewDataHolder;
import com.example.tourism.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IsmiliaHotelRecycle extends AppCompatActivity {

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mdatabaseReference;
    private FirebaseRecyclerAdapter<DataModel, FirebaseViewDataHolder> firebaseRecyclerAdapter;
    private FirebaseRecyclerOptions<DataModel> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ismilia_hotel_recycle);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mRecyclerView = findViewById(R.id.recycleHotel_ismalia);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseReference = mFirebaseDatabase.getReference("data").child("ismailia").child("hotels");

        showData();

    }

    private void showData() {

        options = new FirebaseRecyclerOptions.Builder<DataModel>().setQuery(mdatabaseReference, DataModel.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DataModel, FirebaseViewDataHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewDataHolder Holder, int i, @NonNull DataModel dataModel) {

                Holder.setDetails(getApplicationContext(), dataModel.getName(), dataModel.getImg(), dataModel.getDes());

            }

            @NonNull
            @Override
            public FirebaseViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_of_data, parent, false);

                FirebaseViewDataHolder viewDataHolder = new FirebaseViewDataHolder(itemView);
                viewDataHolder.setOnClickListener(new FirebaseViewDataHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int postion) {

                        Toast.makeText(IsmiliaHotelRecycle.this, "Click Item", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onItemLongClick(View view, int postion) {

                        Toast.makeText(IsmiliaHotelRecycle.this, "Long Click", Toast.LENGTH_SHORT).show();

                    }
                });

                return viewDataHolder;
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseRecyclerAdapter != null) {
            firebaseRecyclerAdapter.startListening();
        }
    }
}
