package edu.washington.apache78.FlashStudy.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.*;
import android.widget.*;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.NoteProgress;


public class TermFragment extends Fragment {

	public TermFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_term, container, false);

		NoteProgress progress = (NoteProgress)getArguments().getSerializable("progress");
        ((TextView)v.findViewById(R.id.termText)).setText(progress.getCurrentCard().term);

		return v;
	}
}
