package `in`.indilabz.student_union.rest

import `in`.indilabz.student_union.model.ShopResult
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class ItemDataSourceFactory(var selectedCategory : Int) : DataSource.Factory<Int, ShopResult>() {

    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ShopResult>>()

    override fun create(): DataSource<Int, ShopResult> {
        val itemDataSource = ItemDataSource(selectedCategory)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, ShopResult>> {
        return itemLiveDataSource
    }
}