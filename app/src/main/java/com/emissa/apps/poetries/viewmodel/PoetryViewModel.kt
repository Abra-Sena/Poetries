package com.emissa.apps.poetries.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emissa.apps.poetries.database.PoemDatabaseRepository
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.network.PoetryRepository
import com.emissa.apps.poetries.utils.NetworkState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PoetryViewModel (
    private val poemRepo: PoetryRepository,
    private val databaseRepo: PoemDatabaseRepository,
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val _poemLiveData: MutableLiveData<NetworkState> = MutableLiveData(NetworkState.LOADING)
    val poemsData: LiveData<NetworkState> get() = _poemLiveData

    private val _poem: MutableLiveData<Poem> = MutableLiveData()
    val poem: LiveData<Poem> get() = _poem

    fun setPoemDetail(poem: Poem) {
        _poem.value = poem
    }
    fun getPoemDetails() : Poem? = poem.value

    private val _authorsLiveData: MutableLiveData<NetworkState> = MutableLiveData(NetworkState.LOADING)
    val authorsData: LiveData<NetworkState> get() = _authorsLiveData

    private val _titlesLiveData: MutableLiveData<NetworkState> = MutableLiveData(NetworkState.LOADING)
    val titlesData: LiveData<NetworkState> get() = _titlesLiveData

    private suspend fun loadPoemsFromDatabase() {
        try {
            val localData = databaseRepo.getPoemByTitle(title = "")
            _poemLiveData.postValue(NetworkState.SUCCESS(localData, isLocal = true))
        } catch (exc: Exception) {
            _poemLiveData.postValue(NetworkState.ERROR(exc))
        }
    }


    fun getPoemByAuthors(author: String) {
        _authorsLiveData.postValue(NetworkState.LOADING)

        viewModelSafeScope.launch(ioDispatcher) {
            try {
                val response = poemRepo.getPoemByAuthor(author)

                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.Main) {
                            _authorsLiveData.postValue(NetworkState.SUCCESS(it))
                        }
                    } ?: throw Exception("No response, Retrieving all authors from end point!")
                } else throw Exception("Unsuccessful, Request Retrieving all authors from end point!")
            } catch (e: Exception) {
                Log.e("viewmodel", "getAuthors: errror", e)
                _authorsLiveData.postValue(NetworkState.ERROR(e))
            }
        }
    }


    fun getPoemByTitle(title: String) {
        _poemLiveData.postValue(NetworkState.LOADING)
        viewModelSafeScope.launch(ioDispatcher) {
            try {
                val response = poemRepo.getPoemByTitle(title)

                if (response.isSuccessful) {
                    response.body()?.let {
                        withContext(Dispatchers.Main) {
                            _poemLiveData.postValue(NetworkState.SUCCESS(it))
                        }
                    } ?: throw Exception("No response, Searching for Poem with title $title")
                } else throw Exception("Unsuccessful, Searching for Poem with title $title!")
            } catch (e: Exception) {
                _poemLiveData.postValue(NetworkState.ERROR(e))
                loadPoemsFromDatabase()
            }
        }
    }

    fun getRandomPoem() {
        _poemLiveData.postValue(NetworkState.LOADING)

        viewModelSafeScope.launch(ioDispatcher) {
            try {
                val response = poemRepo.getRandomPoem()

                if (response.isSuccessful) {
                    response.body()?.let { poems->
                        withContext(Dispatchers.Main) {
                            _titlesLiveData.postValue(NetworkState.SUCCESS(poems[0]))
                            _poem.value = poems[0]
                            Log.i("ViewModel", response.body().toString())
                        }
                    } ?: throw Exception("No response, Retrieving a random poem")
                } else throw Exception("Unsuccessful, Request Retrieving a random poem!")
            } catch (e: Exception) {
                _titlesLiveData.postValue(NetworkState.ERROR(e))
                // get a random poem from local database if offline
            }
        }
    }
}