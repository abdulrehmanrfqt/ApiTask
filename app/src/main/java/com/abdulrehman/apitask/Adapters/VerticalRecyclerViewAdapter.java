package com.abdulrehman.apitask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abdulrehman.apitask.Adapters.Models.HorizontalModel;
import com.abdulrehman.apitask.Adapters.Models.VerticalModel;
import com.abdulrehman.apitask.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRVViewHolder> {

    Context context;
    ArrayList<VerticalModel> arrayList;

    public VerticalRecyclerViewAdapter(Context context, ArrayList<VerticalModel> arrayList)
    {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public VerticalRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new VerticalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRVViewHolder holder, int position) {

        final VerticalModel verticalModel = arrayList.get(position);
        String title = verticalModel.getTitle();
        ArrayList<HorizontalModel> singleItem = verticalModel.getArrayList();

        holder.textViewTitle.setText(title);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context, singleItem);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,verticalModel.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VerticalRVViewHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;
        TextView textViewTitle;
        Button btnMore;

        public VerticalRVViewHolder(View itemView)
        {
            super(itemView);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerview1);
            textViewTitle = (TextView)itemView.findViewById(R.id.txtTitle1);
            btnMore = (Button)itemView.findViewById(R.id.btnMore);
        }
    }
}
