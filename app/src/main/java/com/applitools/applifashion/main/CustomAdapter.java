package com.applitools.applifashion.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter {
    private final List<Shoe> shoes;
    private final Context context;

    public CustomAdapter(final Context context, final List<Shoe> shoes) {
        this.context = context;
        this.shoes = shoes;
    }

    @Override
    public ShoeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row_layout,
                parent, false);
        // set the view's size, margins, paddings and layout parameters
        ShoeViewHolder vh = new ShoeViewHolder(v); // pass the view to View Holdercontext
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // set the data in items

        final ShoeViewHolder shoeViewHolder = (ShoeViewHolder) holder;
        // implement setOnClickListener event on item view.
        final Shoe shoe = shoes.get(position);
        shoeViewHolder.setCurrentPrice(shoe.getCurrentPrice());
        shoeViewHolder.setShoeName(shoe.getName());
        final Drawable shoeImage = context.getResources().getDrawable(shoe.getImageId());
        shoeViewHolder.setShoeImage(shoeImage);

        if (shoe.getOldPrice() == null) {
            shoeViewHolder.hideDiscountInfo();
        } else {
            shoeViewHolder.setOldPrice(shoe.getOldPrice());

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open another activity on item click
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("shoe", shoe); // put image data in Intent
                context.startActivity(intent); // start Intent
            }
        });
    }
    @Override
    public int getItemCount() {
        return shoes.size();
    }
    public class ShoeViewHolder extends RecyclerView.ViewHolder {
        // init the item view's

        private ImageView discountFlag;
        private ImageView shoeImage;
        private ImageView oneDayLeftFlag;
        private TextView shoeName;
        private TextView currentPrice;
        private TextView oldPrice;

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
            this.oldPrice.setText(oldPrice);
            this.oldPrice.setPaintFlags(this.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }
}