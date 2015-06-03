package edu.washington.apache78.FlashStudy.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.washington.apache78.FlashStudy.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class FlashCardActivityFragment extends Fragment {

    public FlashCardActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flash_card, container, false);
    }
}
