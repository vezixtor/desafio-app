package br.com.vezixor.test.webeleven.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.vezixor.test.webeleven.R;
import br.com.vezixor.test.webeleven.adapter.ItemHighlightsAdapter;
import br.com.vezixor.test.webeleven.model.MusicTrack;
import br.com.vezixor.test.webeleven.model.SearchResponse;
import br.com.vezixor.test.webeleven.service.ItunesService;
import br.com.vezixor.test.webeleven.service.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HighlightsFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HighlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighlightsFragment extends Fragment {
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private SearchResponse data;

    public HighlightsFragment() {}

    public static HighlightsFragment newInstance() {
        HighlightsFragment fragment = new HighlightsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return new HighlightsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        String lookingFor = "possible";
        RetrofitService.getInstance()
                .create(ItunesService.class)
                .search(lookingFor)
                .enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        data = response.body();
                        if (data != null) {
                            recyclerView.setAdapter(new ItemHighlightsAdapter(data.getResults(), mListener));
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Log.i("HighlightsFragment", "deu ruim");
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_highlights, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvContacts);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(MusicTrack item);
    }
}
