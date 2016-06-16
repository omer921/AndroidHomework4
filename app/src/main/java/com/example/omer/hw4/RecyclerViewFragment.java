package com.example.omer.hw4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.HashMap;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerView;

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private MovieData movieData;
    private OnFragmentInteractionListener mListener;
    private MyRecyclerViewAdapter mRecyclerViewAdapter;


    TextView title;
    TextView description;
    TextView year;
    ImageView pictureView;
    RatingBar ratingView;

    private Button clearAll;
    private Button selectAll;
    private Button delete;

    Fragment mContent;

    MovieData data;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment RecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewFragment newInstance(int num) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, num);

       // args.put(ARG_PARAM2, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            Log.d("Param", mParam1 + "");
            mParam2 = getArguments().getInt(ARG_PARAM2);

            setRetainInstance(true);



        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {


        View rootView;
        int num = getArguments().getInt(ARG_PARAM1);

        //mRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(), movieData.getMoviesList());

        if (savedInstanceState != null) {
            Bundle retBundle = savedInstanceState.getBundle("movieBundle");
        }
        else {movieData = new MovieData(true);}


         if (num == -1) {
            rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
            // Inflate the layout for this fragment

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);

            mRecyclerView.setHasFixedSize(true);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





            mRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(), movieData.getMoviesList());

            mRecyclerView.setAdapter(mRecyclerViewAdapter);



            mRecyclerViewAdapter.SetOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    mListener.onFragmentInteraction(position);

                }

                @Override
                public void onItemLongClick(View view, int position) {
                    //Log.d("Click", "Long Click");
                    movieData.addMovie(position);
                    mRecyclerViewAdapter.notifyItemInserted(position + 1);

                }
            });


            delete = (Button) rootView.findViewById(R.id.delete);
            clearAll = (Button) rootView.findViewById(R.id.clearAll);
            selectAll = (Button) rootView.findViewById(R.id.selectAll);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < movieData.getSize(); i++) {
                        boolean selected = (boolean) movieData.getItem(i).get("selection");
                        if (selected) {
                            mRecyclerViewAdapter.notifyItemRemoved(i);
                            movieData.removeMovie(i);
                            i--;
                        }

                    }
                    //mRecyclerViewAdapter.notifyDataSetChanged();
                }
            });

            clearAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < movieData.getSize(); i++) {

                        movieData.getItem(i).put("selection", false);
                    }
                    mRecyclerViewAdapter.notifyDataSetChanged();
                }
            });

            selectAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < movieData.getSize(); i++) {

                        movieData.getItem(i).put("selection", true);
                    }
                    mRecyclerViewAdapter.notifyDataSetChanged();

                }
            });
            //defaultAnimation();
             itemAnimator();
             adapterAnimator();

        }
        else {
            rootView = inflater.inflate(R.layout.fragment_movie, container, false);
            title = (TextView) rootView.findViewById(R.id.title);
            description = (TextView) rootView.findViewById(R.id.description);
            year = (TextView) rootView.findViewById(R.id.year);
            pictureView = (ImageView) rootView.findViewById(R.id.pictureView);
            ratingView = (RatingBar) rootView.findViewById(R.id.ratingView);

            HashMap movieItem = movieData.getItem(num);

            title.setText((String) movieItem.get("name"));
            description.setText((String) movieItem.get("description"));
            year.setText((String) movieItem.get("year"));

            int imageId = (Integer) movieItem.get("image");
            pictureView.setImageResource(imageId);
            double progress = (double) movieItem.get("rating");
            //progress /= 20;
            ratingView.setProgress((int)progress);
        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
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




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
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


    private void defaultAnimation() {
        DefaultItemAnimator animator = new DefaultItemAnimator();

        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);

        mRecyclerView.setItemAnimator(animator);

    }

    private void itemAnimator() {
        SlideInLeftAnimator animator = new SlideInLeftAnimator();
        animator.setInterpolator(new OvershootInterpolator());

        animator.setAddDuration(300);
        animator.setRemoveDuration(300);

        mRecyclerView.setItemAnimator(animator);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void adapterAnimator() {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecyclerViewAdapter);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mRecyclerView.setAdapter(scaleAdapter);
       // mRecyclerViewAdapter.notifyDataSetChanged();
    }

}

