package com.mutkuensert.fragmenttestingwithhilt.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.fragmenttestingwithhilt.data.ImagesModel
import com.mutkuensert.fragmenttestingwithhilt.data.source.ImagesRepository
import com.mutkuensert.fragmenttestingwithhilt.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: ImagesRepository): ViewModel() {
    private val _data = MutableLiveData<Resource<ImagesModel>>(Resource.standby(null))
    val data get() = _data

    fun requestImages(search: String, page: Int){
        _data.value = Resource.loading(null)

        viewModelScope.launch(Dispatchers.IO){
            _data.postValue(repository.requestImages(search, page))
        }
    }
}