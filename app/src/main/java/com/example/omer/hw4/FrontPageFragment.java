package com.example.omer.hw4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrontPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrontPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrontPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    private Button aboutMeButton;
    private Button task1Button;

    private OnFragmentInteractionListener mListener;

    public FrontPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FrontPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FrontPageFragment newInstance(int num) {
        FrontPageFragment fragment = new FrontPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, num);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("page", getArguments().getInt(ARG_PARAM1));
        //Save the fragment's state here
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView;
//        if (savedInstanceState != null) {
//            mParam1 = savedInstanceState.getInt("page");
//        }
//        else {
//            //Log.d("SavedInstance", "is null");
//        }
//
        int option = getArguments().getInt(ARG_PARAM1);
        mParam1 = option;
        //this.setRetainInstance(true);


        if (mParam1 == 0) {
            rootView = inflater.inflate(R.layout.fragment_front_page, container, false);
            aboutMeButton = (Button) rootView.findViewById(R.id.aboutButton);
            aboutMeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentInteraction(1);
                }
            });

            task1Button = (Button) rootView.findViewById(R.id.task1Button);
            task1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFragmentInteraction(2);
                }
            });
        }
        else {
            rootView = inflater.inflate(R.layout.cover_page, container, false);
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int num);
    }
}
