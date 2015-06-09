package edu.washington.apache78.FlashStudy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.NoteProgress;


public class TermFragment extends Fragment {
	boolean last;
	public TermFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_term, container, false);
		last = false;

		if(last){
			v.findViewById(R.id.btnDefNextTerm).setVisibility(View.GONE);
			v.findViewById(R.id.btnDone).setVisibility(View.VISIBLE);
		}
		NoteProgress progress = (NoteProgress)getArguments().getSerializable("progress");
        ((TextView)v.findViewById(R.id.termText)).setText(progress.getCurrentCard().term);

		return v;
	}
}
