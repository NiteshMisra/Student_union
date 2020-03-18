package `in`.indilabz.student_union.activity

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.adapter.HistoryAdapter
import `in`.indilabz.student_union.databinding.ActivityHistoryBinding
import `in`.indilabz.student_union.model.ShopResult
import `in`.indilabz.student_union.response.ShopResponse
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter
    private lateinit var historyList: ArrayList<ShopResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_history)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.historyRcv.layoutManager = LinearLayoutManager(this)
        binding.historyRcv.setHasFixedSize(true)
        getData()
        binding.swipe.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        binding.swipe.isRefreshing = true
        historyList = ArrayList()
        RetrofitInstance.getDiscountRetrofit(
            INDIMaster.newApi().history(
                INDIPreferences.user()!!.id.toString(),
                0
            )
            , result
        )
    }

    private val result = { _: Int, bool: Boolean, value: ShopResponse ->

        binding.swipe.isRefreshing = false

        if (bool) {

            val datumList = ArrayList<ShopResult>()
            for (item: ShopResult in value.result!!) {
                datumList.add(item)
            }
            historyList.addAll(datumList)

            adapter = HistoryAdapter(historyList)
            binding.historyRcv.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
