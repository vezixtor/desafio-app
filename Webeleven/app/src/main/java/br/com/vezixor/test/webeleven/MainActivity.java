package br.com.vezixor.test.webeleven;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import br.com.vezixor.test.webeleven.dialog.MusicDialogFragment;
import br.com.vezixor.test.webeleven.fragment.HighlightsFragment;
import br.com.vezixor.test.webeleven.fragment.SearchResultsFragment;
import br.com.vezixor.test.webeleven.fragment.WaitingContentFragment;
import br.com.vezixor.test.webeleven.model.MusicTrack;

public class MainActivity extends AppCompatActivity
        implements HighlightsFragment.OnListFragmentInteractionListener,
        SearchResultsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commitFragment();
    }

    private void commitFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_highlights, new HighlightsFragment())
                .add(R.id.fragment_content, new WaitingContentFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view, menu);

        SearchView mSearchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content, SearchResultsFragment.newInstance(query, null))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void onListFragmentInteraction(MusicTrack musicTrack) {
        MusicDialogFragment dialog = MusicDialogFragment.newInstance(musicTrack);
        dialog.show(getFragmentManager(), "TAG");
    }
}
