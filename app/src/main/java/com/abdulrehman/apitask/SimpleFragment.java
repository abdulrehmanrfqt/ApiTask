package com.abdulrehman.apitask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;


public class SimpleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.simple_text, container, false);

        TextView tv = (TextView) root.findViewById(R.id.tv) ;
//        Bundle args = getArguments();
        tv.setText(R.string.mockingbird_lyric);
        return root;
    }
}
