package com.study.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.study.paging3.data.SampleData
import com.study.paging3.repository.SampleRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {
    /**
     * (고급 코드 랩 7p)
     *  cachedIn 메서드를 PagingData 흐름에 적용하여 viewModelScope 내에서 flow를 활성 상태로 유지한다.
     *
     * (기본 코드 랩 7p)
     * cachedIn 메서드에 viewModelScope를 전달하여 변경 사항에도 페이징 상태를 유지한다.
     *
     * (기타)
     * cachedIn을 사용하여 캐싱을 할 수 있다.
     */
    fun getContent() : Flow<PagingData<SampleData>> {
        return SampleRepository().getSamplePagingSource().cachedIn(viewModelScope)
    }
}