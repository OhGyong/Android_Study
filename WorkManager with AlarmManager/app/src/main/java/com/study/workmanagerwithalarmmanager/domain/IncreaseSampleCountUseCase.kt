package com.study.workmanagerwithalarmmanager.domain

import javax.inject.Inject

class IncreaseSampleCountUseCase @Inject constructor(private val sampleRepository: SampleRepository) {
    suspend operator fun invoke() {
        sampleRepository.incrementCount()
    }
}