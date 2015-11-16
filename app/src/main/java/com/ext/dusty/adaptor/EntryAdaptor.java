package com.ext.dusty.adaptor;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ext.dusty.R;
import com.ext.dusty.component.module.EntryModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EntryAdaptor extends RecyclerView.Adapter<EntryAdaptor.ViewHolder> implements View.OnClickListener {

    List<EntryModule.Entry> entries;

    @Inject
    public EntryAdaptor(List<EntryModule.Entry> entries) {
        this.entries = entries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.entry.setText(entries.get(position).description);
        holder.entry.setTag(position);
        holder.entry.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Intent intent = new Intent();
        intent.setClass(v.getContext(), entries.get(position).target);
        v.getContext().startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_entry)
        TextView entry;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
