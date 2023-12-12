package com.example.pitchchamp;

// Import statements
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.ViewHolder> {

    private final List<Recording> recordingList;

    public RecordingAdapter(List<Recording> recordingList) {
        this.recordingList = recordingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recording_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recording recording = recordingList.get(position);
        holder.textSongTitle.setText(recording.getTitle());
        holder.textDateRecorded.setText(recording.getDateRecorded());
        holder.textRecordingLength.setText(recording.getRecordingLength());
    }

    @Override
    public int getItemCount() {
        return recordingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textSongTitle, textDateRecorded, textRecordingLength;

        ViewHolder(View itemView) {
            super(itemView);
            textSongTitle = itemView.findViewById(R.id.textSongTitle);
            textDateRecorded = itemView.findViewById(R.id.textDateRecorded);
            textRecordingLength = itemView.findViewById(R.id.textRecordingLength);
        }
    }
}
