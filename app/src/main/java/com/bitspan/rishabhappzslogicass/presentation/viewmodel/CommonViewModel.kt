package com.bitspan.rishabhappzslogicass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _count: MutableLiveData<Int> = MutableLiveData()
    val count: LiveData<Int>
        get() = _count

    var openBool = false

    fun saveCountToStorage(count: Int) {
        viewModelScope.launch {
            dataStore.put(DataStoreManagerImpl.exitCount, count)
        }
    }

    fun getCountFromStorage() = dataStore.get(DataStoreManagerImpl.exitCount, 1).asLiveData()

    fun updateCount(count: Int) {
        _count.postValue(count)
    }

}