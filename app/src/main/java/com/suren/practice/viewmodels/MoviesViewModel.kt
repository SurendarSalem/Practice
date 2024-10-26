package com.suren.practice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suren.practice.data.ApiResult
import com.suren.practice.data.MovieRepository
import com.suren.practice.data.Post
import com.suren.practice.toResultFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private var _posts: MutableLiveData<ApiResult<List<Post>>>? =
        MutableLiveData<ApiResult<List<Post>>>()
    var posts: LiveData<ApiResult<List<Post>>>? = _posts

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            val postFlow = toResultFlow {
                movieRepository.getMovies()
            }
            postFlow.collectLatest {
                _posts?.value = it
            }
        }
    }

}