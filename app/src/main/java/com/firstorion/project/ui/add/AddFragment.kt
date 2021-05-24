package com.firstorion.project.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firstorion.project.OrionApplication
import com.firstorion.project.R
import com.firstorion.project.viewmodel.post.PostsViewModel
import com.firstorion.project.viewmodel.post.PostsViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private lateinit var title: EditText
    private lateinit var body: EditText
    private lateinit var postButton: Button

    private val postsViewModel: PostsViewModel by viewModels {
        PostsViewModelFactory((this.activity?.application as OrionApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_add, container, false)

        title = v.findViewById(R.id.newTitle)
        body = v.findViewById(R.id.newBody)
        postButton = v.findViewById(R.id.post)
        postButton.setOnClickListener {
            postToServer()
        }
        return v
    }

    private fun postToServer() {
        postsViewModel.addPost((0..10).random(), body.text.toString(), title.text.toString())
    }

}