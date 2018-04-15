package br.com.vezixor.test.webeleven.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.vezixor.test.webeleven.R;
import br.com.vezixor.test.webeleven.fragment.SearchResultsFragment;
import br.com.vezixor.test.webeleven.model.MusicTrack;

public class ItemSearchResultsAdapter extends RecyclerView.Adapter<ItemSearchResultsAdapter.ViewHolder> {

    private final List<MusicTrack> mValues;
    private final SearchResultsFragment.OnListFragmentInteractionListener mListener;


    public ItemSearchResultsAdapter(List<MusicTrack> items, SearchResultsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ItemSearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_search_results, parent, false);
        return new ItemSearchResultsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemSearchResultsAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mMusicName.setText(mValues.get(position).getTrackName());
        holder.mArtistName.setText(mValues.get(position).getArtistName());


        if (holder.mView != null) {
            Picasso.get().load(mValues.get(position).getArtworkUrl100()).into(holder.mImage);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public final TextView mMusicName;
        public final TextView mArtistName;
        public MusicTrack mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.img);
            mMusicName = (TextView) view.findViewById(R.id.muisc_track);
            mArtistName = (TextView) view.findViewById(R.id.artist_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mArtistName.getText() + "'";
        }
    }
}
