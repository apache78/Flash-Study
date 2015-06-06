package edu.washington.apache78.FlashStudy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.*;
import edu.washington.apache78.FlashStudy.models.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlashCardActivityFragment extends Fragment {

	public FlashCardActivityFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_flash_card, container, false);

		((Button)v.findViewById(R.id.startFlashCard)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NotesManager nm = NotesManager.getInstance();
				if(nm.getNotes().size() > 0) {
					Note a = nm.getNotes().get(0);
					NoteProgress progress = new NoteProgress(nm.getNotes().get(0));
					Bundle bundle = new Bundle();
					bundle.putSerializable("progress", progress);

					TermFragment termFragment = new TermFragment();
					termFragment.setArguments(bundle);

					FlashCardActivityFragment.this.getActivity().getFragmentManager().beginTransaction().remove(FlashCardActivityFragment.this).commit();
				}
			}
		});

		return v;
	}
}
