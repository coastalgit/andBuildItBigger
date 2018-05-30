package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private AdView mAdView;

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
        mAdView = (AdView) root.findViewById(R.id.adView);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.btn_telljoke)
    public void btnTellJoke_onClick(Button button){
        displayAd();
    }

    private void loadJoke(){
        if (mCheckBoxFromCloud.isChecked())
            ((MainActivity)getActivity()).fetchJokeFromCloud();
        else
            ((MainActivity)getActivity()).fetchJokeFromJavaLibrary();
    }

    private void displayAd(){
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.setAdListener(new AdListener(){
              @Override
              public void onAdLoaded() {
                  super.onAdLoaded();
                  loadJoke();
              }
          });
        mAdView.loadAd(adRequest);
    }
}
