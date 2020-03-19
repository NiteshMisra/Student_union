package `in`.indilabz.student_union.rest

import `in`.indilabz.student_union.model.ShopResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private var itemPagedList: LiveData<PagedList<ShopResult>>? = null
    private lateinit var liveDataSource: LiveData<PageKeyedDataSource<Int, ShopResult>>

    suspend fun getImages(selectedCategory: Int): LiveData<PagedList<ShopResult>> {
        return withContext(Dispatchers.IO) {
            fetchImages(selectedCategory)
        }
    }

    private fun fetchImages(selectedCategory: Int): LiveData<PagedList<ShopResult>> {
        val itemDataSourceFactory = ItemDataSourceFactory(selectedCategory)
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        val pagedListConfig = (PagedList.Config.Builder())
            .setEnablePlaceholders(false)
            .setPageSize(20).build()
        itemPagedList = (LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
            .build()
        return itemPagedList!!
    }

}