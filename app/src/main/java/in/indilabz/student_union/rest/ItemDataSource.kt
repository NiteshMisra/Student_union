package `in`.indilabz.student_union.rest

import `in`.indilabz.student_union.INDIMaster
import `in`.indilabz.student_union.model.ShopResult
import `in`.indilabz.student_union.response.ShopResponse
import `in`.indilabz.yorneeds.utils.INDIPreferences
import androidx.paging.PageKeyedDataSource

class ItemDataSource(var selectedCategory : Int) : PageKeyedDataSource<Int, ShopResult>() {

    private var pageLoaded = 0
    private var isNextLoading = true

    override fun loadInitial( params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ShopResult>) {

        RetrofitInstance.getDiscountRetrofit(
            INDIMaster.newApi().discount(
                INDIPreferences.user()!!.id.toString(),
                pageLoaded,
                selectedCategory.toString()
            )
        ) { _: Int, bool: Boolean, value: ShopResponse ->

            if (bool && value.success) {

                val result = value.result!!

                if (result.size < 10){
                    isNextLoading = false
                }
                pageLoaded += 1
                callback.onResult(value.result!!,null,pageLoaded)
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ShopResult>) {

        if (isNextLoading){

            RetrofitInstance.getDiscountRetrofit(
                INDIMaster.newApi().discount(
                    INDIPreferences.user()!!.id.toString(),
                    pageLoaded,
                    selectedCategory.toString()
                )
            ) { _: Int, bool: Boolean, value: ShopResponse ->

                if (bool && value.success) {

                    val result = value.result!!
                    pageLoaded += 1
                    callback.onResult(result,pageLoaded)
                    if (result.size < 10){
                        isNextLoading = false
                    }
                }
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ShopResult>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}