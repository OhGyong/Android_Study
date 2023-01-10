package com.study.recyclerviewpaginationremoveitem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.recyclerviewpaginationremoveitem.data.SampleDao
import com.study.recyclerviewpaginationremoveitem.data.SampleData
import com.study.recyclerviewpaginationremoveitem.data.SampleDatabase
import com.study.recyclerviewpaginationremoveitem.repository.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val sampleListSizeObserve: MutableLiveData<Int> = MutableLiveData()
    val sampleListObserve: MutableLiveData<ArrayList<SampleData>> = MutableLiveData()
    val itemDeleteObserve: MutableLiveData<Unit> = MutableLiveData()

    fun getSampleListSize(db: SampleDatabase) {
        viewModelScope.launch(Dispatchers.IO) {
            sampleListSizeObserve.postValue(db.getSampleDao().getListSize())
        }
    }

    fun getSampleList(db: SampleDatabase, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            sampleListObserve.postValue(db.getSampleDao().getList(page) as ArrayList<SampleData>)
        }
    }

    fun setItemDelete(db: SampleDatabase, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDeleteObserve.postValue(db.getSampleDao().itemDelete(id))
        }
    }

    fun getContent(db: SampleDao): Flow<PagingData<SampleData>> {
        return SampleRepository().getSampleData(db).cachedIn(viewModelScope)

    }
}