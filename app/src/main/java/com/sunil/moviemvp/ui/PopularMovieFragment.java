package com.sunil.moviemvp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sunil.moviemvp.MainApplication;
import com.sunil.moviemvp.R;
import com.sunil.moviemvp.di.MovieModule;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.repository.LocalRepository;
import com.sunil.moviemvp.repository.RemoteRepository;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 20-01-2018.
 */

public class PopularMovieFragment extends Fragment implements MovieContract.View{

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private MovieGridAdapter movieGridAdapter;

    @Inject
    LocalRepository localRepository;
    @Inject
    RemoteRepository remoteRepository;
    @Inject
    MoviePresenter moviePresenter;


    public static PopularMovieFragment newInstance() {
        PopularMovieFragment fragment = new PopularMovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PopularMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle arg = getArguments();
        }

        dependencyInject();
        moviePresenter.subscribe();
        moviePresenter.attachView(this);
    }

    public void dependencyInject(){
        MainApplication.getComponent(getActivity().getApplicationContext())
                .getMovieComponent(new MovieModule(getActivity().getApplicationContext())).inject(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);

        if (moviePresenter != null) {
            moviePresenter.getMovieFromService();
        }else{
            Log.v("Popular Fragment", "Presenter getting null");
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        myRecyclerView.setHasFixedSize(true);
        // use a grid layout manager
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        myRecyclerView.setLayoutManager(mLayoutManager);
        moviePresenter.loadMovie();
    }

    @Override
    public void onLoadMovieOk(List<MovieEntity> movieList) {
        if (movieList!= null){
            // specify an adapter
            movieGridAdapter = new MovieGridAdapter(getActivity(), movieList);
            myRecyclerView.setAdapter(movieGridAdapter);
        }
    }

    @Override
    public void showLoadErrorMessage(String errorMsg) {
        Toast.makeText(getActivity(), "Error: "+errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView(boolean isShow) {
        Toast.makeText(getActivity(), "Empty View: "+isShow, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        moviePresenter.unSubscribe();
    }
}
