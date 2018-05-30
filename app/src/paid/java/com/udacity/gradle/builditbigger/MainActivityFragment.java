package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.btn_telljoke)
    Button mBtnTellJoke;

    @BindView(R.id.checkbox_fromcloud)
    CheckBox mCheckBoxFromCloud;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    private void loadJoke(){
        if (mCheckBoxFromCloud.isChecked())
            ((MainActivity)getActivity()).fetchJokeFromCloud();
        else
            ((MainActivity)getActivity()).fetchJokeFromJavaLibrary();
    }

    @OnClick(R.id.btn_telljoke)
    public void btnTellJoke_onClick(Button button){
        loadJoke();
    }
}
