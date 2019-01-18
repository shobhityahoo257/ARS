package com.example.shobhitverma.ars.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shobhitverma.ars.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_layout,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder productviewHolder, int position) {
        Product product = productList.get(position);

        productviewHolder.textViewTitle.setText(product.getTitle());
        productviewHolder.textViewDesc.setText(product.getDescription());
        productviewHolder.textViewRating.setText(String.valueOf(product.getRating()));
        productviewHolder.textViewPrice.setText(String.valueOf(product.getPrice()));

        productviewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImg()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle,textViewDesc,textViewRating,textViewPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewPrice= itemView.findViewById(R.id.textViewPrice);
            textViewRating= itemView.findViewById(R.id.textViewRating);
        }
    }
}