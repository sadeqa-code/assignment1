package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends BaseAdapter {
    ArrayList<Contact> contacts =new ArrayList<>();
    Activity activity;
    private TextView tvName;
    private TextView tvNumber;
    private TextView tvAddress;

    public ContactsAdapter(ArrayList<Contact> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;


    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(activity).inflate(R.layout.simple_list_item_1, null);
        tvName = v.findViewById(R.id.tvName);
        tvNumber = v.findViewById(R.id.tvNumber);
        tvAddress = v.findViewById(R.id.tvAddress);

        tvName.setText(contacts.get(i).getName());
        tvNumber.setText(contacts.get(i).getNumber());
        tvAddress.setText(contacts.get(i).getAddress());
        return v;

    }
}
