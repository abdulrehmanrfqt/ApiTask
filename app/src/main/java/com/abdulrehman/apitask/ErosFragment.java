package com.abdulrehman.apitask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abdulrehman.apitask.Adapters.Models.VerticalModel;
import com.abdulrehman.apitask.Adapters.VerticalRecyclerViewAdapter;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;

public class ErosFragment extends Fragment {

    public static final String TAG = "TAG";
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter Vadapter;
    //    HorizontalRecyclerViewAdapter Hadapter;
    ArrayList<VerticalModel> arrayListVertical;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.eros_fragment, container, false);

        verticalRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);
        verticalRecyclerView.setHasFixedSize(true);

        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        Vadapter = new VerticalRecyclerViewAdapter(getActivity(), arrayListVertical);
        verticalRecyclerView.setAdapter(Vadapter);

        return root;
    }

    ErosFragment(ArrayList<VerticalModel> arrayListVertical) {
        this.arrayListVertical = arrayListVertical;
    }

    public static ErosFragment getInstance(ArrayList<VerticalModel> arrayListVertical) {
        return new ErosFragment(arrayListVertical);
    }

}
