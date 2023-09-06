package ec.aplication.friendlink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class About extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public About() {

    }


    public static About newInstance(String param1, String param2) {
        About fragment = new About();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        Linkify.addLinks((TextView) view.findViewById(R.id.correo1), Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks((TextView) view.findViewById(R.id.gitHub1), Linkify.WEB_URLS);
        Linkify.addLinks((TextView) view.findViewById(R.id.correo2), Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks((TextView) view.findViewById(R.id.gitHub2), Linkify.WEB_URLS);
        Linkify.addLinks((TextView) view.findViewById(R.id.correo3), Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks((TextView) view.findViewById(R.id.gitHub3), Linkify.WEB_URLS);
        return view;
    }

}