package com.amitthakare.socialstatussaver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.amitthakare.socialstatussaver.R;
import com.amitthakare.socialstatussaver.utils.LayManager;


public class WAGuideFragment extends Fragment {
    ImageView help1, help2, help3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.frag_wapp_guide, container, false);

        ((TextView)localView.findViewById(R.id.help)).setTypeface(LayManager.getTypeface(getActivity()));
        ((TextView)localView.findViewById(R.id.textView1)).setTypeface(LayManager.getTypeface(getActivity()));
        ((TextView)localView.findViewById(R.id.textView2)).setTypeface(LayManager.getSubTypeface(getActivity()));
        ((TextView)localView.findViewById(R.id.textView3)).setTypeface(LayManager.getTypeface(getActivity()));
        ((TextView)localView.findViewById(R.id.textView4)).setTypeface(LayManager.getSubTypeface(getActivity()));
        ((TextView)localView.findViewById(R.id.textView5)).setTypeface(LayManager.getTypeface(getActivity()));
        ((TextView)localView.findViewById(R.id.textView6)).setTypeface(LayManager.getSubTypeface(getActivity()));

        help1 = localView.findViewById(R.id.help1);
        help2 = localView.findViewById(R.id.help2);
        help3 = localView.findViewById(R.id.help3);

        Glide.with(getActivity())
                .load(R.drawable.wa_help_1)
                .into(help1);

        Glide.with(getActivity())
                .load(R.drawable.help_2)
                .into(help2);

        Glide.with(getActivity())
                .load(R.drawable.help_3)
                .into(help3);
        return localView;
    }
}
