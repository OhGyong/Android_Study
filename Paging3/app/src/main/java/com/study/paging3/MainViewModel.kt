package com.study.paging3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.paging3.data.SampleData
import com.study.paging3.data.SampleDatabase
import com.study.paging3.repository.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    fun getContent() : Flow<PagingData<SampleData>> {
        return SampleRepository().getSamplePagingSource().cachedIn(viewModelScope)
    }
}