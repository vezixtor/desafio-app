package br.com.vezixor.test.webeleven.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.vezixor.test.webeleven.R;
import br.com.vezixor.test.webeleven.model.MusicTrack;

public class MusicDialogFragment extends DialogFragment {
    private MusicTrack musicTrack;
    private View view;

    public static MusicDialogFragment newInstance(MusicTrack musicTrack) {
        MusicDialogFragment fragment = new MusicDialogFragment();
        fragment.setMusicTrack(musicTrack);
        return fragment;
    }

    public void setMusicTrack(MusicTrack musicTrack) {
        this.musicTrack = musicTrack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_music, container, false);
        inflateView();
        return view;
    }

    private void inflateView() {
        ImageView mImage = (ImageView) view.findViewById(R.id.imageView3);
        Picasso.get().load(musicTrack.getArtworkUrl100()).into(mImage);

        TextView mMusicTrack = (TextView) view.findViewById(R.id.music_track);
        mMusicTrack.setText(musicTrack.getTrackName());

        TextView mArtistName = (TextView) view.findViewById(R.id.artist_name);
        mArtistName.setText(musicTrack.getArtistName());

        TextView mType = (TextView) view.findViewById(R.id.type);
        mType.setText(musicTrack.getKind());

        TextView mGenre = (TextView) view.findViewById(R.id.genre);
        mGenre.setText(musicTrack.getPrimaryGenreName());

        TextView mPrice = (TextView) view.findViewById(R.id.price);
        mPrice.setText(musicTrack.getTrackPrice().toString());
    }
}
