package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
@SuppressWarnings({"WeakerAccess", "unused", "ConstantConditions", "NullableProblems"})
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

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
        mAdView = root.findViewById(R.id.adView);

        return root;
    }

    @OnClick(R.id.btn_telljoke)
    public void btnTellJoke_onClick(Button button){
        ((MainActivity)getActivity()).displayProgressBar(true);
        displayAd();
    }

    private void loadJoke(){
        if (mCheckBoxFromCloud.isChecked())
            ((MainActivity)getActivity()).fetchJokeFromCloud();
        else
            ((MainActivity)getActivity()).fetchJokeFromJavaLibrary();
    }

    private void displayAd(){

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.setAdListener(new AdListener(){
              @Override
              public void onAdLoaded() {
                  Log.d(TAG, "onAdLoaded: ");
                  super.onAdLoaded();
                  loadJoke();
              }
          });

        mAdView.loadAd(adRequest);
    }
}
