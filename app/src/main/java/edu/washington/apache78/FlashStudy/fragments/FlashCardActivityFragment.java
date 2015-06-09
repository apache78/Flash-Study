package edu.washington.apache78.FlashStudy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import edu.washington.apache78.FlashStudy.R;
import edu.washington.apache78.FlashStudy.includes.NoteProgress;
import edu.washington.apache78.FlashStudy.includes.NotesManager;
import edu.washington.apache78.FlashStudy.models.Note;

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
		//retrieve the list of terms, either here or in CardAdapter;


		final GridView gridview = (GridView) v.findViewById(R.id.gridView);
		gridview.setAdapter(new CardAdapter(getActivity()));
		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String term = (String) (gridview.getItemAtPosition(position));

			}
		});
				((Button) v.findViewById(R.id.startFlashCard)).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						NotesManager nm = NotesManager.getInstance();
						if (nm.getNotes().size() > 0) {
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
