package com.rogok.natifetestapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rogok.natifetestapp.data.GiphyRepository
import com.rogok.natifetestapp.models.GiphyImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GalleryViewModel @ViewModelInject constructor(
    private val giphyRepository: GiphyRepository
): ViewModel() {

    /*private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    *//*val giphyImages = currentQuery.switchMap { queryString ->  //it: String
        giphyRepository.getSearchResult(queryString).cachedIn(viewModelScope)
    }*//*

    //we have here tweaked the data type from Flow<PagingData<DoggoImageModel>>
    // and mapped it to Flow<PagingData<String>> you can always return whatever you want
    val giphyImagesFlow = giphyRepository.getSearchResult(DEFAULT_QUERY)
        .map { it*//*pagingData -> pagingData.map {it.url}*//* }
        .cachedIn(viewModelScope)*/

    val giphyImages: Flow<PagingData<GiphyImage>> =
        giphyRepository.getGiphyImagesFlowDb().cachedIn(viewModelScope)



    /*val searchQuery = MutableStateFlow("")

    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
    val hideCompleted = MutableStateFlow(false)

    private val tasksFlow = combine(
        searchQuery,
        sortOrder,
        hideCompleted
    ) { query, sortOrder, hideCompleted ->
        Triple(query, sortOrder, hideCompleted)
    }.flatMapLatest { (query, sortOrder, hideCompleted) ->
        taskDao.getTasks(query, sortOrder, hideCompleted)
    }

    val tasks = tasksFlow.asLiveData()*/

    /*fun searchImages(query: String) {
        giphyImagesFlow.combine()
    *//*currentQuery.value = query*//*
    }*/

    companion object {
        private const val DEFAULT_QUERY = "burgers"
    }
}