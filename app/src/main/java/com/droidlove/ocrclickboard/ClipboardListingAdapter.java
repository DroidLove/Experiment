package com.droidlove.ocrclickboard;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.droidlove.ocrclickboard.databinding.ItemClipboardBinding;

import java.util.ArrayList;

/**
 * Created by itrs-203 on 11/17/17.
 */

public class ClipboardListingAdapter extends RecyclerView.Adapter<ClipboardListingAdapter.CustomViewHolder> {
    private ArrayList<String> data;
    private Service activity;

    public ClipboardListingAdapter(Service activity, ArrayList<String> data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ClipboardListingAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        ItemClipboardBinding binding = DataBindingUtil.inflate(li, R.layout.item_clipboard, parent, false);
        return new ClipboardListingAdapter.CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ClipboardListingAdapter.CustomViewHolder holder, final int position) {

        holder.binding.textviewClipboardResult.setText(data.get(position));

        holder.binding.relativeClipboard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", data.get(position));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(activity, "Text Copied", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void updateListing(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ItemClipboardBinding binding;

        CustomViewHolder(ItemClipboardBinding bindig) {
            super(bindig.getRoot());
            binding = bindig;
        }
    }

}
