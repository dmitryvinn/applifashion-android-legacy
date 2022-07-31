package com.applitools.applifashion.main.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.applitools.applifashion.main.R;
import com.applitools.applifashion.main.activities.ProductActivity;
import com.applitools.applifashion.main.beans.Shoe;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter {
    private final List<Shoe> shoes;
    private final Context context;

    private static final int FOOTER_VIEW = 1;

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public ProductAdapter(final Context context, final List<Shoe> shoes) {
        this.context = context;
        this.shoes = shoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view;
        if (viewType == FOOTER_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_layout, parent, false);
            return  new FooterViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row_layout,
                    parent, false);
            return new ShoeViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            if (holder instanceof ShoeViewHolder) {
                final ShoeViewHolder shoeViewHolder = (ShoeViewHolder) holder;
                final Shoe shoe = shoes.get(position);
                shoeViewHolder.setCurrentPrice(shoe.getCurrentPrice());
                shoeViewHolder.setShoeName(shoe.getName());
                @SuppressLint("UseCompatLoadingForDrawables") final Drawable shoeImage
                        = context.getResources().getDrawable(shoe.getImageId());
                shoeViewHolder.setShoeImage(shoeImage);

                if (shoe.getOldPrice() == null) {
                    shoeViewHolder.hideDiscountInfo();
                } else {
                    shoeViewHolder.setOldPrice(shoe.getOldPrice());

                }

                holder.itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("shoe", shoe);
                    context.startActivity(intent);
                });
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;

                StaggeredGridLayoutManager.LayoutParams layoutParams
                        = (StaggeredGridLayoutManager.LayoutParams) footerViewHolder.itemView.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                }
                layoutParams.setFullSpan(true);
                footerViewHolder.itemView.setLayoutParams(layoutParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (shoes == null) {
            return 0;
        }

        if (shoes.size() == 0) {
            //Return 1 here to show nothing
            return 1;
        }

        // Add extra view to show the footer view
        return shoes.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == shoes.size()) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    public class ShoeViewHolder extends RecyclerView.ViewHolder {
        // init the item view's

        private final ImageView discountFlag;
        private final ImageView shoeImage;
        private final ImageView oneDayLeftFlag;
        private final TextView shoeName;
        private final TextView currentPrice;
        private final TextView oldPrice;

        public ShoeViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            discountFlag = (ImageView) itemView.findViewById(R.id.discount_icon);
            oneDayLeftFlag = (ImageView) itemView.findViewById(R.id.one_day_left_icon);
            shoeImage = (ImageView) itemView.findViewById(R.id.shoe_image);
            shoeName = (TextView) itemView.findViewById(R.id.shoe_name);
            currentPrice = (TextView) itemView.findViewById(R.id.current_price);
            oldPrice = (TextView) itemView.findViewById(R.id.old_price);

        }

        public void hideDiscountInfo() {
            discountFlag.setVisibility(View.INVISIBLE);
            oldPrice.setVisibility(View.GONE);
            oneDayLeftFlag.setVisibility(View.INVISIBLE);

        }

        public void setShoeImage(final Drawable shoeImage) {
            this.shoeImage.setImageDrawable(shoeImage);
        }

        public void setShoeName(String shoeName) {
            this.shoeName.setText(shoeName);
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice.setText(currentPrice);
        }

        public void setOldPrice(String oldPrice) {
            discountFlag.setVisibility(View.VISIBLE);
            this.oldPrice.setVisibility(View.VISIBLE);
            oneDayLeftFlag.setVisibility(View.VISIBLE);

            this.oldPrice.setText(oldPrice);
            this.oldPrice.setPaintFlags(this.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}