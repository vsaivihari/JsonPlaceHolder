package com.firstorion.project.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.OrionApplication
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post
import com.firstorion.project.ui.add.AddFragment
import com.firstorion.project.ui.user.UserFragment
import com.firstorion.project.viewmodel.post.PostsViewModel
import com.firstorion.project.viewmodel.post.PostsViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PostsFragment : Fragment(), PostsRVAdapter.OnPostClickedListener {

    private val postsAdapter = PostsRVAdapter(this)
    private lateinit var fab: FloatingActionButton

    private val postsViewModel: PostsViewModel by viewModels {
        PostsViewModelFactory((this.activity?.application as OrionApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_posts, container, false)

        fab = v.findViewById(R.id.fab)
        fab.setOnClickListener {
            launchAddFragment()
        }
        val rv = v.findViewById<RecyclerView>(R.id.postsRecyclerView)
        rv.layoutManager = LinearLayoutManager(v.context)
        rv.adapter = postsAdapter
        subscribeObservers()
        return v
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.getPosts()
    }

    private fun launchAddFragment() {
        val addFragment = AddFragment()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, addFragment)?.addToBackStack(null)?.commit()
    }

    private fun subscribeObservers() {
        postsViewModel.posts.observe(viewLifecycleOwner, {
            postsAdapter.posts = it as MutableList<Post>
            postsAdapter.notifyDataSetChanged()
        })
    }

    //Not sure, why activity is preferred over ViewModel??

    override fun onPostClicked(post: Post) {
        val userFragment = UserFragment.newInstance(post.userId)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, userFragment)?.addToBackStack(null)?.commit()
    }
}