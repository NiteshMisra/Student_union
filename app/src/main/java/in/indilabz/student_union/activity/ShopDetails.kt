package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.utils.QRCodeHelper
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.model.Shop
import `in`.indilabz.student_union.databinding.LayoutShopBottomBinding
import `in`.indilabz.student_union.model.Discount
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
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

        val shop: Shop = Gson().fromJson(value, Shop::class.java)

        binding.toolbar.setNavigationOnClickListener{
            this.finish()
        }

        binding.name.text = shop.shop_name
        binding.offer.text = shop.discount+" %"
        binding.categories.text = shop.category

        Glide.with(this)
            .load(shop.user_img)
            .into(binding.preview)

        binding.address.text = shop.current_address

        val uuid = UUID.randomUUID().toString()

        val discount = Discount(
            INDIPreferences.user()!!.student_id,
            shop.discount)

        val bitmap = QRCodeHelper.newInstance(this)
            .setContent(INDIMaster.gson.toJson(discount))
            .setErrorCorrectionLevel(ErrorCorrectionLevel.Q)
            .qrcOde

        //generateQR(INDIMaster.gson.toJson(discount))
        binding.qrCode.setImageBitmap(bitmap)

        binding.studentName.text = INDIPreferences.user()!!.name
    }

    private fun generateQR(value: String){

        val renderOption = RenderOption()
        renderOption.content = value // content to encode
        renderOption.size = 800 // size of the final QR code image
        renderOption.borderWidth = 20 // width of the empty space around the QR code
        renderOption.ecl = ErrorCorrectionLevel.M // (optional) specify an error correction level
        renderOption.patternScale = 0.35f // (optional) specify a scale for patterns
        renderOption.roundedPatterns = true // (optional) if true, blocks will be drawn as dots instead
        renderOption.clearBorder = true // if set to true, the background will NOT be drawn on the border area

        val result = AwesomeQrRenderer.renderAsync(renderOption, { result ->
            if (result.bitmap != null) {
                // play with the bitmap
                binding.qrCode.setImageBitmap(result.bitmap)
            }
        }, {
                exception -> exception.printStackTrace()
        })
    }
}
