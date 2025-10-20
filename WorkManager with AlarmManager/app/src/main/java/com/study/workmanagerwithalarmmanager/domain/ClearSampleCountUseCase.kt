package com.study.workmanagerwithalarmmanager.domain

import javax.inject.Inject

class ClearSampleCountUseCase @Inject constructor(private val sampleRepository: SampleRepository) {
    suspend operator fun invoke() {
        sampleRepository.clearCount()
    }
}