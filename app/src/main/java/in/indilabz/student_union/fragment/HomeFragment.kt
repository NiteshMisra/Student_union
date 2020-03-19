@file:Suppress("DEPRECATION")

package `in`.indilabz.student_union.fragment

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.R
import `in`.indilabz.student_union.adapter.ItemAdapter
import `in`.indilabz.student_union.adapter.ShopAdapter
import `in`.indilabz.student_union.model.ShopResult
import `in`.indilabz.student_union.response.CategoryResponse
import `in`.indilabz.student_union.response.ShopResponse
import `in`.indilabz.student_union.rest.HomeViewModel
import `in`.indilabz.student_union.rest.RetrofitInstance
import `in`.indilabz.student_union.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gallery.GridSpacingItemDecoration
import com.gallery.extra.Coroutines

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var discount: TextView
    private lateinit var amount: TextView
    private lateinit var searchCategory: Spinner
    private lateinit var alloted: TextView
    private var selectedCategory: Int = 0
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view!!.findViewById(R.id.recycler)
        swipe = view.findViewById(R.id.swipe)
        searchCategory = view.findViewById(R.id.home_category)
        alloted = view.findViewById(R.id.allotted)
        discount = view.findViewById(R.id.discount)
        amount = view.findViewById(R.id.amount)

        viewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)

        getData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe.setOnRefreshListener {
            selectedCategory = 0
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
                for (item in result2) {
                    list.add(item.title)
                }

                val categoryAdapter = ArrayAdapter<String>(
                    activity!!,
                    android.R.layout.simple_spinner_item,
                    list
                )

                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                searchCategory.adapter = categoryAdapter
                searchCategory.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            pos: Int,
                            id: Long
                        ) {
                            if (pos != 0) {
                                swipe.isRefreshing = true
                                selectedCategory = pos - 1
                                load()
                            }
                        }

                    }

                load()

            }
        }
    }

    private fun load() {
        val mAdapter = ItemAdapter()
        Coroutines.main {
            viewModel.getImages(selectedCategory).observe(this,
                Observer<PagedList<ShopResult>> { photos ->
                    mAdapter.submitList(photos)
                    alloted.text = "0"
                    discount.text = "0"
                    amount.text = "0"
                })
            swipe.isRefreshing = false
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(GridSpacingItemDecoration(2, 5, false))
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(false)
            adapter = mAdapter
        }
    }

}
