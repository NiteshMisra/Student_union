package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.model.Shop
import `in`.indilabz.student_union.adapter.ShopAdapter
import `in`.indilabz.student_union.databinding.ActivityMainBinding
import `in`.indilabz.student_union.response.DiscountInfoResponse
import `in`.indilabz.student_union.response.DiscountResponse
import `in`.indilabz.student_union.rest.APIHelper
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val datum: ArrayList<Shop> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        setSupportActionBar(binding.toolbar!!)

        val manager = GridLayoutManager(this, 2)

        binding.recycler.layoutManager = manager
        binding.recycler.setHasFixedSize(true)

        binding.profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.search.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (datum.size > 0){
                    val filterList : ArrayList<Shop> = ArrayList()
                    for (item in datum){
                        if (item.category.toLowerCase(Locale.getDefault()).contains(s!!.toString().toLowerCase(Locale.getDefault()))){
                            filterList.add(item)
                        }
                    }
                    adapter.updateList(filterList)

                }
            }

        })

        getData()

        binding.swipe.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {

        binding.swipe.isRefreshing = true

        binding.allotted.text = "..."
        binding.discount.text = "..."
        binding.amount.text = "..."

        RetrofitInstance.getDiscountRetrofit(
            INDIMaster.api().discount(
                INDIPreferences.user()!!.student_id
            )
            , result
        )

        RetrofitInstance.getDiscountInfoRetrofit(
            INDIMaster.api().discountInfo(
                INDIPreferences.user()!!.student_id
            )
            , stats
        )
    }

    private val result = { _: Int, bool: Boolean, value: DiscountResponse ->

        binding.swipe.isRefreshing = false

        if (bool) {

            val datumList = ArrayList<Shop>()
            for (item: Shop in value.results) {
                datumList.add(item)
            }

            datum.clear()
            datum.addAll(datumList)

            binding.allotted.text = datum.size.toString()

            adapter = ShopAdapter(datum)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private val stats = { _: Int, bool: Boolean, value: DiscountInfoResponse ->

        if (bool) {

            binding.discount.text = value.discount
            binding.amount.text = value.amount

        } else {

            binding.allotted.text = "0"
            binding.discount.text = "0"
            binding.amount.text = "0"

            Toaster.longt("Error while getting stats")
        }
    }

}
