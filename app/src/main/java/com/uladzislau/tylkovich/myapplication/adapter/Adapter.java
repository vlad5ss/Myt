package com.uladzislau.tylkovich.myapplication.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uladzislau.tylkovich.myapplication.R;


public final class Adapter extends RecyclerView.Adapter<Holder> {
    private Context context;
    private Cursor cursor;

    public Adapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.car_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setTextInViewHolder(position, context, cursor);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
