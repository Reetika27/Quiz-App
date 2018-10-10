package com.example.swarangigaurkar.learnersapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;
    private OnItemClickListner mListener;
   public interface OnItemClickListner
   {
       void onItemClick(int position);
   }

   public void setOnItemClickListener(OnItemClickListner listener)
   {
       mListener=listener;
   }

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.productList = productList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        //View view1=inflater.inflate(R.layout.list_layout,  null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ProductViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.textViewtitle.setText(product.getTitle());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(product.getPrice());
        Glide.with(mCtx).load(product.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public CardView cardView;
        TextView textViewtitle, textViewRating, textViewPrice;

        public ProductViewHolder(View itemView,final OnItemClickListner listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewtitle = itemView.findViewById(R.id.textViewTitle);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                   if(listener!=null)
                   {
                       int position =getAdapterPosition();
                       if(position!=RecyclerView.NO_POSITION)
                       {
                           listener.onItemClick(position);
                       }
                   }

               }
           });

        }
    }
}
