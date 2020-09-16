package com.abdulrehman.apitask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abdulrehman.apitask.Adapters.Models.HorizontalModel;
import com.abdulrehman.apitask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRVViewHolder> {


    Context context;
    ArrayList<HorizontalModel> arrayList;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public HorizontalRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRVViewHolder holder, int position)
    {
        final HorizontalModel mhorizontalModel = arrayList.get(position);

        String imageUrl = mhorizontalModel.getmImageUrl();
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.imageViewThumb);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(context, mhorizontalModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HorizontalRVViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewThumb;

        public HorizontalRVViewHolder(View itemView){
            super(itemView);
            imageViewThumb = (ImageView)itemView.findViewById(R.id.ivThumb);
        }
    }
}
