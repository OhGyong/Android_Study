package com.study.datastore.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.study.datastore.MainApplication.DataModule.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingRepository @Inject constructor(private val context: Context) {
    object DataStoreResult {
        const val SET_NAME = "Set Name Success"
        const val SET_WEIGHT = "Set Weight Success"
    }

    private val nameKey = stringPreferencesKey("name") // 이름
    private val weightKey = stringPreferencesKey("weight") // 체중(공복 체중)

    /**
     * 이름 설정
     */
    suspend fun setName(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getName(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 몸무게 설정
     */
    suspend fun setWeight(weight: String): String {
        context.dataStore.edit {
            it[weightKey] = weight
        }
        return DataStoreResult.SET_WEIGHT
    }

    suspend fun getWeight(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            // firstInitKey 값이 없을 경우 앱 최초 진입으로 판단
            it[weightKey] ?: "WeightNull"
        }
    }
}
