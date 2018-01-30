package com.example.amiotj.tp0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private DatabaseReference databaseReference;
    private MessageAdapter messageAdapter;

    private EditText inputEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        inputEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputEditText.getText().toString().isEmpty()) {
                    DatabaseReference newData = databaseReference.push();
                    newData.setValue(new Message(inputEditText.getText().toString(), "Fabrice Jacque", 0L));
                    inputEditText.setText("");
                }
            }
        });

        messageAdapter = new MessageAdapter(new ArrayList<Message>());
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat/messages");
        databaseReference.addValueEventListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.removeEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d("Test", "onDataChange: " + dataSnapshot);
        List<Message> items = new ArrayList<>();
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            items.add(postSnapshot.getValue(Message.class));
        }
        messageAdapter.setData(items);

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
