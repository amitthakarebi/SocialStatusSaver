package com.amitthakare.socialstatussaver.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amitthakare.socialstatussaver.utils.AdUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.amitthakare.socialstatussaver.PreviewActivity;
import com.amitthakare.socialstatussaver.R;
import com.amitthakare.socialstatussaver.model.DataModel;
import com.amitthakare.socialstatussaver.utils.Utils;

import java.io.File;
import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {
    private Activity activity;
    private File file;
    ArrayList<DataModel> mData;

    public DownloadAdapter(Activity paramActivity, ArrayList<DataModel> paramArrayList) {
        this.mData = paramArrayList;
        this.activity = paramActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.download_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DataModel jpast = (DataModel) this.mData.get(position);
        this.file = new File(jpast.getFilePath());
        if (!this.file.isDirectory()) {
            if (!Utils.getBack(jpast.getFilePath(), "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()) {
                try {
                    Glide.with(this.activity).load(this.file).apply(new RequestOptions().placeholder(android.R.color.black).error(android.R.color.black).optionalTransform(new RoundedCorners(1))).into(holder.imagevi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.imagePlayer.setVisibility(View.VISIBLE);
            } else if (!Utils.getBack(jpast.getFilePath(), "((\\.3ga|\\.aac|\\.aif|\\.aifc|\\.aiff|\\.amr|\\.au|\\.aup|\\.caf|\\.flac|\\.gsm|\\.kar|\\.m4a|\\.m4p|\\.m4r|\\.mid|\\.midi|\\.mmf|\\.mp2|\\.mp3|\\.mpga|\\.ogg|\\.oma|\\.opus|\\.qcp|\\.ra|\\.ram|\\.wav|\\.wma|\\.xspf)$)").isEmpty()) {
                holder.imagePlayer.setVisibility(View.GONE);
            } else if (!Utils.getBack(jpast.getFilePath(), "((\\.jpg|\\.png|\\.gif|\\.jpeg|\\.bmp)$)").isEmpty()) {
                holder.imagePlayer.setVisibility(View.GONE);
                Glide.with(this.activity).load(this.file).apply(new RequestOptions().placeholder(android.R.color.black).error(android.R.color.black).optionalTransform(new RoundedCorners(1))).into(holder.imagevi);
            }

            holder.deleteIV.setOnClickListener(v -> delete(position, activity));

            holder.shareIV.setOnClickListener(v -> share(jpast.getFilePath(), activity));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private RelativeLayout cardView;
        private ImageView imagePlayer;
        private ImageView imagevi, shareIV, deleteIV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imagevi = ((ImageView) itemView.findViewById(R.id.imageView));
            this.imagePlayer = ((ImageView) itemView.findViewById(R.id.iconplayer));
            this.cardView = ((RelativeLayout) itemView.findViewById(R.id.card_view));
            this.cardView.setOnClickListener(this);
            this.shareIV = itemView.findViewById(R.id.shareIV);
            this.deleteIV = itemView.findViewById(R.id.deleteIV);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(activity, PreviewActivity.class);
            intent.putParcelableArrayListExtra("images", mData);
            intent.putExtra("position", getAdapterPosition());
            intent.putExtra("statusdownload", "download");

                if (!AdUtils.isloadFbAd) {
                    AdUtils.adCounter++;
                    AdUtils.showInterAd(activity, intent);
                } else {
                    AdUtils.adCounter++;
                    AdUtils.loadFbInterAd(activity, intent);
                }

        }
    }

    void share(String path, Activity activity) {
        Utils.mShare(path, activity);
    }

    void delete(final int position, Activity activity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Are You Sure to Delete this File?");
        alertDialog.setPositiveButton("Yes", (dialog, which) -> {
            dialog.dismiss();
            File file = new File(mData.get(position).getFilePath());
            if (file.exists()) {
                file.delete();
                mData.remove(position);
                notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        alertDialog.show();
    }
}
