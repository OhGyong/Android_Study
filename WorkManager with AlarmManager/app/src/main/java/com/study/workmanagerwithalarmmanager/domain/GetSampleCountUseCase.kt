package com.study.workmanagerwithalarmmanager.domain

import javax.inject.Inject

class GetSampleCountUseCase @Inject constructor(private val sampleRepository: SampleRepository) {
    operator fun invoke() = sampleRepository.get()
}