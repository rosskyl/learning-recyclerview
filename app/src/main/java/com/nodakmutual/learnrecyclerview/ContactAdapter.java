package com.nodakmutual.learnrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Sam Stutsman on March, 2015.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> contacts;

    // Constructor class creates the adapter and passses in what we will need from our Fragment
    public ContactAdapter(List<Contact> contacts) {

        this.contacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // This line inflates the layout which will be repeated
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        // We create an instance of our ViewHolder so we can handle click events.
        ViewHolder viewHolder = new ViewHolder(view, new ViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // We can handle click events in here, but only if we have
                // registered the listeners in the ViewHolder
            }
        });
        return viewHolder;
    }

    @Override
    // This method assigns the values from the contacts list we passed in to the views
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(contacts.get(position).getName());
        holder.tvAddress.setText(contacts.get(position).getAddress());
    }

    @Override
    // This simple method is needed for the RecyclerView.
    public int getItemCount() {
        return contacts.size();
    }

    // This is our ViewHolder inner class. Very important!
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener listener;

        TextView tvName;
        TextView tvAddress;


        // We map our views, and assign listeners in the ViewHolder constructor
        public ViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);

            tvName.setOnClickListener(this);
            tvAddress.setOnClickListener(this);
        }

        // This method is just to pass on the onClick event to our individual items! Neat!
        @Override
        public void onClick(View view) {

            listener.onItemClick(view, getPosition());
        }

        // This is the interface which forces our Adapter to implement the OnClickListener
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }

}
