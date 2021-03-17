package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextNumber;
    EditText editTextAddress;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ContactsAdapter adapter;
    ArrayList<Contact> contacts;
    ListView listViewContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.txtName);
        editTextNumber = findViewById(R.id.txtNumber);
        editTextAddress = findViewById(R.id.txtAddress);
        listViewContact= findViewById(R.id.contactLv);
        contacts= new ArrayList<>();

    }

    public void saveToFirebase(View v) {
        String Name = editTextName.getText().toString();
        String Number = editTextNumber.getText().toString();
        String Address = editTextAddress.getText().toString();

//Create a new user with a first and last name
        Map<String, Object> contact = new HashMap<>();
        contact.put("contant_Name", Name);
        contact.put("contact_Number", Number);
        contact.put("contact_Address", Address);

// Add a new document with a generated ID
        db.collection("contacts")
                .add(contact)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

        db.collection("contacts")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for(DocumentSnapshot userSnapshot : task.getResult()){
                                String name = (String) userSnapshot.get("name");
                                String phone = (String) userSnapshot.get("phone");
                                String address = (String) userSnapshot.get("address");

                                Contact user = new Contact(name,phone,address);
                                contacts.add(user);
                            }
                            adapter=new ContactsAdapter(contacts,MainActivity.this);
                            listViewContact.setAdapter(adapter);

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });




                }
    }


