package com.suren.practice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suren.practice.R
import com.suren.practice.data.Post
import com.suren.practice.databinding.FragmentMoviesBinding
import com.suren.practice.ui.adapters.PostsAdapter
import com.suren.practice.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsAdapter = PostsAdapter({
            onClickedPost(it)
        }, R.layout.post_item)
    }

    private fun onClickedPost(post: Post) {
        Toast.makeText(context, "Clicked ${post.title}", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPosts.layoutManager = LinearLayoutManager(context)
        binding.rvPosts.adapter = postsAdapter
        observerPosts()
    }

    private fun observerPosts() {
        moviesViewModel.posts?.observe(viewLifecycleOwner) {
            postsAdapter.submitList(it.data)
        }
    }
}