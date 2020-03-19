package `in`.indilabz.student_union.adapter

import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.activity.ShopDetails
import `in`.indilabz.student_union.fragment.HomeFragment
import `in`.indilabz.student_union.model.ShopResult
import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson

class ItemAdapter : PagedListAdapter<ShopResult, ItemAdapter.ItemViewHolder>(DIFF_CALLBACK) {


    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val parent: CardView = itemView.findViewById(R.id.parent)
        val preview:ImageView = itemView.findViewById(R.id.preview)
        val storeName: TextView = itemView.findViewById(R.id.storeName)
        val currentAddress : TextView = itemView.findViewById(R.id.category)
        val offer: TextView = itemView.findViewById(R.id.offer)
    }

    companion object {

        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<ShopResult>() {
            override fun areItemsTheSame(oldItem: ShopResult, newItem: ShopResult):Boolean {
                return oldItem.id.toString() === newItem.id.toString()
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ShopResult, newItem: ShopResult):Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_shop,parent,false)
        return ItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val shop : ShopResult = getItem(position)!!

        holder.storeName.text = shop.shopResponseModel.name
        holder.currentAddress.text = shop.shopResponseModel.currentAddress
        holder.offer.text = "${shop.discount}%"

        try {

            if (shop.shopResponseModel.shop_image == "" || shop.shopResponseModel.shop_image.isEmpty()){
                holder.preview.scaleType = ImageView.ScaleType.CENTER_INSIDE
                holder.preview.setImageResource(R.drawable.ic_shopping_cart)
            }else{
                Glide
                    .with(holder.preview.context)
                    .load("http://3.19.184.22/student-union/index.php/image/shop/${shop.shopResponseModel.shop_image}/234")
                    .placeholder(R.drawable.ic_shopping_cart)
                    .into(holder.preview)
                holder.preview.scaleType = ImageView.ScaleType.FIT_XY
            }
        }catch (e : Exception){

        }

        holder.parent.setOnClickListener {

            val intent = Intent(holder.parent.context, ShopDetails::class.java)
            intent.putExtra("GSON", Gson().toJson(shop))
            holder.parent.context.startActivity(intent)
        }
    }
}