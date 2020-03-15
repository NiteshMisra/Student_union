package `in`.indilabz.student_union.fragment

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.adapter.ShopAdapter
import `in`.indilabz.student_union.model.ShopResult
import `in`.indilabz.student_union.response.CategoryResponse
import `in`.indilabz.student_union.response.ShopResponse
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class HomeFragment : Fragment() {

    private val datum: ArrayList<ShopResult> = ArrayList()
    private lateinit var adapter: ShopAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var alloted: TextView
    private lateinit var discount: TextView
    private lateinit var amount: TextView
    private lateinit var searchCategory : Spinner
    private var isScroll = false
    private var position  = 0
    private var currentItems : Int = 0
    private var totalItems : Int = 0
    private var scrollOutItems : Int = 0
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view!!.findViewById(R.id.recycler)
        swipe = view.findViewById(R.id.swipe)
        alloted = view.findViewById(R.id.allotted)
        discount = view.findViewById(R.id.discount)
        amount = view.findViewById(R.id.amount)
        progressBar = view.findViewById(R.id.progress)
        searchCategory = view.findViewById(R.id.home_category)

        val manager = GridLayoutManager(this.activity, 2)

        recyclerView.layoutManager = manager
        recyclerView.setHasFixedSize(true)

        progressBar.visibility = View.GONE
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()

                if (isScroll && (currentItems + scrollOutItems == totalItems)){
                    isScroll = false
                    position++
                    progressBar.visibility = View.VISIBLE
                    getData()
                }

            }

        })

        getData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe.setOnRefreshListener {
            position = 0
            getData()
        }
    }

    private fun getData() {

        swipe.isRefreshing = true

        alloted.text = "..."
        discount.text = "..."
        amount.text = "..."

        RetrofitInstance.getCategoryRetrofit(
            INDIMaster.newApi().getCategory(),
            category
        )
    }

    private val category = { _: Int, bool: Boolean, value: CategoryResponse ->

        if (bool) {

            val result2 = value.categoryResults
            if (value.success && result2 != null) {

                val list = ArrayList<String>()
                list.add("Select Category")
                for (item in result2){
                    list.add(item.title)
                }

                val categoryAdapter = ArrayAdapter<String>(
                    activity!!,
                    android.R.layout.simple_spinner_item,
                    list
                )

                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                searchCategory.adapter = categoryAdapter
                searchCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (position != 0){
                            swipe.isRefreshing = true
                            RetrofitInstance.getDiscountRetrofit(
                                INDIMaster.newApi().discount(
                                    INDIPreferences.user()!!.id.toString(),
                                    position,
                                    (position - 1).toString()
                                )
                                , result
                            )
                        }
                    }

                }

                RetrofitInstance.getDiscountRetrofit(
                    INDIMaster.newApi().discount(
                        INDIPreferences.user()!!.id.toString(),
                        position,
                        result2[0].id.toString()
                    )
                    , result
                )

            }
        }
    }

    private val result = { _: Int, bool: Boolean, value: ShopResponse ->

        swipe.isRefreshing = false
        progressBar.visibility = View.GONE

        if (bool) {

            val datumList = ArrayList<ShopResult>()
            for (item: ShopResult in value.result) {
                datumList.add(item)
            }

            datum.clear()
            datum.addAll(datumList)

            alloted.text = datum.size.toString()
            discount.text = "0"
            amount.text = "0"

            if (datum.size == 0){
                Toaster.longt("No Data is available")
            }
            adapter = ShopAdapter(datum)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }


}
