package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.utils.QRCodeHelper
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.model.Shop
import `in`.indilabz.student_union.databinding.LayoutShopBottomBinding
import `in`.indilabz.student_union.model.Discount
import `in`.indilabz.student_union.model.ShopResult
import `in`.indilabz.student_union.response.CategoryResponse
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.github.sumimakito.awesomeqr.AwesomeQrRenderer
import com.github.sumimakito.awesomeqr.RenderResult
import com.github.sumimakito.awesomeqr.option.RenderOption
import com.google.gson.Gson
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import kotlinx.android.synthetic.main.layout_shop_bottom.*
import java.util.*


class ShopDetails : AppCompatActivity() {

    private lateinit var binding: LayoutShopBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.layout_shop_bottom
        )

        val value = intent.getStringExtra("GSON")

        val shop: ShopResult = Gson().fromJson(value, ShopResult::class.java)

        binding.toolbar.setNavigationOnClickListener{
            this.finish()
        }

        binding.name.text = shop.shopResponseModel.name
        binding.offer.text = "${shop.discount}%"
        binding.categories.text = "-"
        binding.studentName.text = INDIPreferences.user()!!.full_name

        try {
            binding.preview.scaleType = ImageView.ScaleType.FIT_XY
            Glide.with(this).load("http://3.19.184.22/student-union/index.php/image/shop/${shop.shopResponseModel.shop_image}/234").into(binding.preview)
        }catch (e : Exception){

        }
        binding.address.text = shop.shopResponseModel.currentAddress

        RetrofitInstance.getCategoryRetrofit(
            INDIMaster.newApi().getCategory()
        ) { _: Int, bool: Boolean, value2: CategoryResponse ->

            if (bool) {

                val result2 = value2.categoryResults
                if (value2.success && result2 != null) {

                    val list = ArrayList<String>()
                    for (item in result2) {
                        list.add(item.title)
                    }

                    binding.categories.text = list[shop.shopResponseModel.category.toInt()]

                }
            }
        }

        val discount = Discount(
            shop.id.toString(),
            shop.studentId.toString(),
            shop.shopResponseModel.id.toString(),
            shop.discount.toString(),
            shop.allowedDiscount.toString())

        val bitmap = QRCodeHelper.newInstance(this)
            .setContent(INDIMaster.gson.toJson(discount))
            .setErrorCorrectionLevel(ErrorCorrectionLevel.Q)
            .qrcOde

        binding.qrCode.setImageBitmap(bitmap)

        binding.studentName.text = INDIPreferences.user()!!.full_name
    }

}
