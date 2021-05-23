package com.firstorion.project.viewmodel.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.firstorion.project.repo.OrionRepository
import com.firstorion.project.repo.user.User
import kotlinx.coroutines.launch

/**
 * This class will get the `User` data from `usersRepo` that will be displayed on the `UsersFragment` fragment.
 *
 * Please do not remove `usersRepo` parameter from the constructor.
 * */
class UsersViewModel(
    private val usersRepo: IUsersRepo
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?> = _user

    fun getUser(userId: Int) {
        viewModelScope.launch {
            val user = usersRepo.getUserWithId(userId)
            _user.value = user
        }

    }

}

class UsersViewModelFactory(private val repository: OrionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}