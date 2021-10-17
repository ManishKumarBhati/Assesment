package com.manishbhati.baseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manishbhati.baseproject.util.Data
import com.manishbhati.baseproject.util.Status
import com.manishbhati.domain.Repository
import com.manishbhati.domain.Response
import com.manishbhati.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    fun getData(): MutableLiveData<Data<List<Response>>> {
        val mutableMainState = MutableLiveData<Data<List<Response>>>()
        viewModelScope.launch {
            mutableMainState.value = Data(responseType = Status.LOADING, error = null, data = null)
            when (val result = withContext(Dispatchers.IO) { repository.getData() }) {
                is Result.Failure -> {
                    mutableMainState.value =
                        Data(responseType = Status.ERROR, error = result.exception, data = null)
                }
                is Result.Success -> {
                    mutableMainState.value =
                        Data(responseType = Status.SUCCESSFUL, data = result.data)
                }
            }
        }
        return mutableMainState
    }

    fun updateData(id: Long, status: Boolean) =
        viewModelScope.launch { repository.update(id, status) }

    override fun onCleared() {
        TODO("Not yet implemented")
    }
}