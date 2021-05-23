package com.firstorion.project.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firstorion.project.OrionApplication
import com.firstorion.project.R
import com.firstorion.project.viewmodel.user.UsersViewModel
import com.firstorion.project.viewmodel.user.UsersViewModelFactory

class UserFragment : Fragment() {

    private lateinit var userName: TextView
    private lateinit var userEmail: TextView

    private val usersViewModel: UsersViewModel by viewModels {
        UsersViewModelFactory((this.activity?.application as OrionApplication).repository)
    }

    companion object {
        const val USER_ID = "userID"

        fun newInstance(userId: Int): UserFragment {
            val args = Bundle()
            args.putInt(USER_ID, userId)
            val userFragment = UserFragment()
            userFragment.arguments = args
            return userFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_user, container, false)

        userName = v.findViewById(R.id.user_name)
        userEmail = v.findViewById(R.id.user_email)
        val userId = arguments?.getInt(USER_ID)
        if (userId != null) {
            usersViewModel.getUser(userId)
        }

        subscribeObservers()
        return v
    }

    private fun subscribeObservers() {
        usersViewModel.user.observe(viewLifecycleOwner, {
            userName.text = it?.userName ?: " "
            userEmail.text = it?.email ?: " "

        })
    }


}