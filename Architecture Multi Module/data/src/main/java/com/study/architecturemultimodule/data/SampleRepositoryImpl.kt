package com.study.architecturemultimodule.data

import com.study.architecturemultimodule.domain.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(): SampleRepository {
    override fun getSampleData(): String = "Sample"
}