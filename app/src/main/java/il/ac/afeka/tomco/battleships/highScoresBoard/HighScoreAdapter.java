package il.ac.afeka.tomco.battleships.highScoresBoard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import il.ac.afeka.tomco.battleships.R;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder> {
    private final List<HighScoreModel> highScoreModels;
    private LayoutInflater mLayoutInflater;

    public HighScoreAdapter(ArrayList<HighScoreModel> highScoreModels, Context context) {
        this.highScoreModels = highScoreModels;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_high_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(highScoreModels.get(position));
    }

    @Override
    public int getItemCount() {
        return highScoreModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final TextView tvScore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.HighScoreName);
            tvScore = itemView.findViewById(R.id.HighScorePoints);
        }

        public void bind(HighScoreModel highScoreModel) {
            tvName.setText(highScoreModel.getName());
            tvScore.setText(highScoreModel.getPoints());
        }
    }
}
