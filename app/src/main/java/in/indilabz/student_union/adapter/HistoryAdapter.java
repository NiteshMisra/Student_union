package in.indilabz.student_union.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Objects;

import in.indilabz.student_union.R;
import in.indilabz.student_union.model.ShopResult;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<ShopResult> historyList;

    public HistoryAdapter(ArrayList<ShopResult> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_history,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        ShopResult item = historyList.get(position);
        holder.shopName.setText(item.getShopResponseModel().getName());
        holder.discount.setText("Discount : \u20B9 "+ String.valueOf(item.getAllowedDiscount()));

        try {
            if (item.getShopResponseModel().getShop_image().isEmpty()){
                holder.shopImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                holder.shopImage.setImageResource(R.drawable.ic_sale);
            }else{

                Glide.with(holder.shopImage.getContext())
                        .load("http://3.19.184.22/student-union/index.php/image/shop/${shop.shopResponseModel.shop_image}/234")
                        .placeholder(R.drawable.ic_sale)
                        .into(holder.shopImage);
                holder.shopImage.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }catch (Exception e){
            Log.e("Image Error", Objects.requireNonNull(e.getMessage()));
        }

        if (position%2 == 0){
            holder.layout.setBackgroundColor(Color.parseColor("#FCFCFC"));
        }else{
            holder.layout.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        ImageView shopImage;
        TextView shopName, discount;
        ConstraintLayout layout;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            shopImage = itemView.findViewById(R.id.history_image);
            shopName = itemView.findViewById(R.id.history_shop_name);
            discount = itemView.findViewById(R.id.history_shop_discount);
            layout = itemView.findViewById(R.id.history_layout);
        }
    }

}
