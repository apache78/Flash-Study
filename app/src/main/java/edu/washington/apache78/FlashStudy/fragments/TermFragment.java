package edu.washington.apache78.FlashStudy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.NoteProgress;
import edu.washington.apache78.FlashStudy.includes.NotesManager;
import edu.washington.apache78.FlashStudy.models.Note;


public class TermFragment extends Fragment {
	boolean last;

	Button prev;
	Button next;
	Button definition;

	public TermFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_term, container, false);
		last = false;

		prev = (Button) v.findViewById(R.id.btnPrev);
		next = (Button) v.findViewById(R.id.btnNext);
		definition = (Button) v.findViewById(R.id.btnDefintion);

		prev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					Log.i("===","PREVIOUS");
			}
		});

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("===","Next");
			}
		});

		definition.setOnClickListener(new View.OnClickListener() {



			@Override
			public void onClick(View v) {

				Log.i("===", "definition");
				NotesManager nm = NotesManager.getInstance();
				Note a = nm.getNotes().get(0);
				NoteProgress progress = new NoteProgress(nm.getNotes().get(0));

				Bundle bundle = new Bundle();
				bundle.putSerializable("progress", progress);

				DefinitionFragment definitionFragment = new DefinitionFragment();
				definitionFragment.setArguments(bundle);


				TermFragment.this.getActivity().getFragmentManager().beginTransaction().replace(R.id.fragment_container, definitionFragment).commit();
			}
		});

		if(last){
			v.findViewById(R.id.btnDefNextTerm).setVisibility(View.GONE);
			v.findViewById(R.id.btnDone).setVisibility(View.VISIBLE);
		}
		NoteProgress progress = (NoteProgress)getArguments().getSerializable("progress");
        ((TextView)v.findViewById(R.id.termText)).setText(progress.getCurrentCard().term);

		return v;
	}
}
