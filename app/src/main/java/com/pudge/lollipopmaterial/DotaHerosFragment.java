package com.pudge.lollipopmaterial;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DotaHerosFragment extends Fragment {

    public DotaHerosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_dota_heros, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new SimpleDotaHerosRecycleViewAdapter(getActivity()));
        return mRecyclerView;
    }

    public static class SimpleDotaHerosRecycleViewAdapter extends RecyclerView.Adapter<SimpleDotaHerosRecycleViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();

        private int mBackground;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            private final View mView;

            public final ImageView mImageView;

            public final TextView mTextview;

            public ViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                mImageView = (ImageView) itemView.findViewById(R.id.heroavatar);
                mTextview = (TextView) itemView.findViewById(R.id.heroname);
            }
        }

        public SimpleDotaHerosRecycleViewAdapter(Context context) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mTextview.setText(DotaHeros.sHerosName[position]);


            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                @SuppressLint("23")
                @TargetApi(23)
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DotaHeroDetailsActivity.class);
                    intent.putExtra(DotaHeroDetailsActivity.POSITION, position);
                    if (Build.VERSION.SDK_INT < 23) {
                        context.startActivity(intent);
                    } else {
                        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity)context).toBundle());
                    }
                }
            });

            Glide.with(holder.mImageView.getContext())
                    .load(DotaHeros.iHerosIcon[position])
                    .fitCenter()
                    .into(holder.mImageView);

        }

        @Override
        public int getItemCount() {
            return DotaHeros.sHerosName.length;
        }

    }

}
