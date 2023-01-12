package com.study.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.paging3.data.SampleData
import com.study.paging3.repository.SampleRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {
    fun getContent() : Flow<PagingData<SampleData>> {
        return SampleRepository().getSamplePagingSource().cachedIn(viewModelScope)
    }
}