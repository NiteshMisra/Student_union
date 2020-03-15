package `in`.indilabz.student_union.adapter

import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.model.Shop
import `in`.indilabz.student_union.activity.ShopDetails
import `in`.indilabz.student_union.model.ShopResult
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson

class ShopAdapter(
    var data: ArrayList<ShopResult>
) : RecyclerView.Adapter<ShopAdapter.ShopVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopVH {

        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShopVH(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ShopVH, position: Int) {

        val shop = data[position]

        holder.storeName.text = shop.shopResponseModel.name
        holder.currentAddress.text = shop.shopResponseModel.currentAddress
        holder.offer.text = "${shop.discount} %"

        Glide.with(holder.preview.context)
            .load(shop.shopResponseModel.shop_image).into(holder.preview)

        holder.parent.setOnClickListener{

            val intent = Intent(holder.parent.context, ShopDetails::class.java)
            intent.putExtra("GSON", Gson().toJson(shop))
            holder.parent.context.startActivity(intent)
        }
    }

    class ShopVH(itemView: View): RecyclerView.ViewHolder(itemView){

        val parent: CardView = itemView.findViewById(R.id.parent)
        val preview:ImageView = itemView.findViewById(R.id.preview)
        val storeName:TextView = itemView.findViewById(R.id.storeName)
        val currentAddress :TextView = itemView.findViewById(R.id.category)
        val offer:TextView = itemView.findViewById(R.id.offer)
    }

}
