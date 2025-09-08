package com.study.architecturemultimodule.domain

import javax.inject.Inject

class GetSampleUseCase @Inject constructor(private val sampleRepository: SampleRepository) {
    operator fun invoke(): String = sampleRepository.getSampleData()
}