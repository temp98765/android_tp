package com.example.amiotj.tp0;

import android.content.Intent;
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

import static com.example.amiotj.tp0.UserStorage.getUserInfo;
import static com.example.amiotj.tp0.UserStorage.isUserLoggedIn;


public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private DatabaseReference databaseReference;
    private MessageAdapter messageAdapter;

    private EditText inputEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isUserLoggedIn(this)) {
            Intent intent = new Intent(this, NamePickerActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        inputEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendMessage();
            }
        });

        messageAdapter = new MessageAdapter(new ArrayList<Message>());
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat/messages");
        databaseReference.addValueEventListener(this);
    }

    private void handleSendMessage() {
        if (!inputEditText.toString().isEmpty()) {
            User user = UserStorage.getUserInfo(this);
            assert(user.name != null);
            assert(user.email != null);
            DatabaseReference newData = databaseReference.push();
            newData.setValue(new Message(inputEditText.getText().toString(), user.name, 0L));
            inputEditText.setText("");
        }
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
