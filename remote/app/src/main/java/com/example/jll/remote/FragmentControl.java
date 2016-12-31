package com.example.jll.remote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentControl.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentControl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentControl extends Fragment {

    public Button btnUp;
    public Button btnDown;
    public Button btnLeft;
    public Button btnRight;
    private Button btnStop;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentControl() {
        // Required empty public constructor
    }

    public static FragmentControl newInstance(String param1, String param2) {
        FragmentControl fragment = new FragmentControl();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_control, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnUp = (Button)getActivity().findViewById(R.id.buttonU);
        btnUp.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                Log.v("MyListViewBase", "你点击了按钮UP");
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(getActivity().getBaseContext(),BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","r");
                        intent.putExtras(data);
                        getActivity().startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(getActivity(),BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        getActivity().startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnLeft = (Button)getActivity().findViewById(R.id.L);
        btnLeft.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(getActivity(),BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","d");
                        intent.putExtras(data);
                        getActivity().startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(getActivity(),BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        getActivity().startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnRight = (Button)getActivity().findViewById(R.id.R);
        btnRight.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(getActivity(),BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","u");
                        intent.putExtras(data);
                        getActivity().startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(getActivity(),BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        getActivity().startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnDown = (Button)getActivity().findViewById(R.id.D);
        btnDown.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(getActivity(),BluetoothService.class);
                        Bundle data = new Bundle();
                        data.putString("control","l");
                        intent.putExtras(data);
                        getActivity().startService(intent);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(getActivity(),BluetoothService.class);
                        Bundle data1 = new Bundle();
                        data1.putString("control","s");
                        intent1.putExtras(data1);
                        getActivity().startService(intent1);
                        break;
                }
                return false;
            }
        });

        btnStop = (Button)getActivity().findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(),BluetoothService.class);
                Bundle data1 = new Bundle();
                data1.putString("control","s");
                intent1.putExtras(data1);
                getActivity().startService(intent1);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
