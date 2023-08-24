package com.example.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAllAdapter extends RecyclerView.Adapter<ContactAllViewHolder> {
    private Context context;
    private List<Contact> contactList;
    private ContactListener listener;

    public ContactAllAdapter(Context context, List<Contact> contactList, ContactListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactAllViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAllViewHolder holder, int position) {

        Contact contact = contactList.get(position);

        String contactName = contact.getName();
        String contactPhone = contact.getNumber();

        holder.userName.setText(contactName);
        holder.userPnone.setText(contactPhone);

        holder.userCard.setOnClickListener(view -> {
            listener.viewUser(contact);

        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
