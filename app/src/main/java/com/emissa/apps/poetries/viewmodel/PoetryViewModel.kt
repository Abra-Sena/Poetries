package com.emissa.apps.poetries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emissa.apps.poetries.network.PoetryRepository
import com.emissa.apps.poetries.utils.NetworkState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PoetryViewModel (
    private val poemRepo: PoetryRepository,
    private val databaseRepo: PoetryRepository,
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {
    private val _poetryLiveData: MutableLiveData<NetworkState> = MutableLiveData(NetworkState.LOADING)
    val poetryData: LiveData<NetworkState> get() = _poetryLiveData

    fun getAuthors() {
        _poetryLiveData.postValue(NetworkState.LOADING)

        viewModelScope.launch(ioDispatcher) {
            checkResponse(
                poemRepo.getAllAuthors(),
                "Retrieving all authors from end point"
            )
        }
    }

    fun getAllPoetry() {
        _poetryLiveData.postValue(NetworkState.LOADING)

        viewModelScope.launch(ioDispatcher) {
            checkResponse(
                poemRepo.getAllPoemByTitle(),
                "Retrieving all poems ordered by title"
            )
        }
    }

    fun getPoemByTitle(title: String) {
        _poetryLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(ioDispatcher) {
            checkResponse(
                poemRepo.getPoemByTitle(title),
                "Searching for Poem with title $title"
            )
        }
    }

    fun getRandomPoem() {
        _poetryLiveData.postValue(NetworkState.LOADING)

        viewModelScope.launch(ioDispatcher) {
            checkResponse(
                poemRepo.getRandomPoem(),
                "Retrieving a random poem"
            )
        }
    }

    fun getMultipleRandomPoem(randNumber: Int) {
        _poetryLiveData.postValue(NetworkState.LOADING)
        viewModelScope.launch(ioDispatcher) {
            checkResponse(
                poemRepo.getMultipleRandomPoem(randNumber),
                "Retrieving $randNumber random poems"
            )
        }
    }

    private suspend fun <T> checkResponse(response: Response<T>, message: String) {
        // think about using inline function for this
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    withContext(Dispatchers.Main) {
                        _poetryLiveData.postValue(NetworkState.SUCCESS(it))
                    }
                } ?: throw Exception("$message -> No response!")
            } else throw Exception("$message -> Unsuccessful!")
        } catch (e: Exception) {
            _poetryLiveData.postValue(NetworkState.ERROR(e))
        }
    }
}