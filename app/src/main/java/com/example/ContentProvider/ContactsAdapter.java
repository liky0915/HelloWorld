package com.example.ContentProvider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Bean.ContactBean;
import com.example.helloworld.R;

import java.util.List;

/**
 * Created by lester.ding on 8/2/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter <ContactsAdapter.ViewHolder> {

    private List<ContactBean> list;

    public ContactsAdapter(List<ContactBean> list) {
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView image;
        TextView name, number;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
        }
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item_layout, parent, false);
        final ContactsAdapter.ViewHolder holder = new ContactsAdapter.ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ContactBean contact = list.get(position);
                Toast.makeText(v.getContext(), "你点击了联系人："+contact.getContactName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        ContactBean item = list.get(position);
        holder.image.setImageResource(item.getContactImageResId());
        holder.name.setText(item.getContactName());
        holder.number.setText(item.getContactNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
