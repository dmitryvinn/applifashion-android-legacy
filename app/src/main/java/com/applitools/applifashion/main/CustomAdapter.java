package com.applitools.applifashion.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter {
    private final List<Shoe> shoes;
    private final Context context;

    private static final int FOOTER_VIEW = 1;

// Define a view holder for Footer view

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
            TextView linkTextView = (TextView) itemView.findViewById(R.id.about_us);
//            linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

//            HyperLink = (TextView)findViewById(R.id.textView1);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public CustomAdapter(final Context context, final List<Shoe> shoes) {
        this.context = context;
        this.shoes = shoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_layout, parent, false);

            FooterViewHolder vh = new FooterViewHolder(v);

//            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
//            layoutParams.setFullSpan(true);
            return vh;
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row_layout,
                    parent, false);
        }
        // infalte the item Layout

        // set the view's size, margins, paddings and layout parameters
        ShoeViewHolder vh = new ShoeViewHolder(v); // pass the view to View Holdercontext
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // set the data in items

        try {
            if (holder instanceof ShoeViewHolder) {
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
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder vh = (FooterViewHolder) holder;

                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) vh.itemView.getLayoutParams();
                if ( layoutParams == null ) {
                    layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                }
                layoutParams.setFullSpan(true);
                vh.itemView.setLayoutParams(layoutParams);
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