package com.bitspan.rishabhappzslogicass.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bitspan.rishabhappzslogicass.storage.DataStoreManager
import com.bitspan.rishabhappzslogicass.storage.DataStoreManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(private val dataStore: DataStoreManager) : ViewModel() {

    var count: Int? = null

    fun saveCount(count: Int) {
        viewModelScope.launch {
            dataStore.put(DataStoreManagerImpl.exitCount, count)
        }
    }

    fun getCount() = dataStore.get(DataStoreManagerImpl.exitCount, 1).asLiveData()

}