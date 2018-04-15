package br.com.vezixor.test.webeleven.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.vezixor.test.webeleven.R;
import br.com.vezixor.test.webeleven.adapter.ItemSearchResultsAdapter;
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
 * {@link SearchResultsFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String lookingForParam;
    private RecyclerView recyclerView;
    private SearchResponse data;

    private OnListFragmentInteractionListener mListener;

    public SearchResultsFragment() {}

    public static SearchResultsFragment newInstance(String param1, String param2) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lookingForParam = getArguments().getString(ARG_PARAM1);
        }
        initData();
    }


    private void initData() {
        if (lookingForParam != null && !lookingForParam.isEmpty()) {
            RetrofitService.getInstance()
                    .create(ItunesService.class)
                    .search(lookingForParam)
                    .enqueue(new Callback<SearchResponse>() {
                        @Override
                        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                            data = response.body();
                            if (data != null) {
                                recyclerView.setAdapter(new ItemSearchResultsAdapter(data.getResults(), mListener));
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchResponse> call, Throwable t) {
                        }
                    });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.search_results_rv);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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
