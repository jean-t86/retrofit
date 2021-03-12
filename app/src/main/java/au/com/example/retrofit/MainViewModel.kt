package au.com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.example.retrofit.util.ApiResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _repos: MutableLiveData<ApiResponse<List<Repo>>> = MutableLiveData()
    val repos: LiveData<ApiResponse<List<Repo>>>
        get() = _repos

    init {
        val gitHubService = getGitHubService()
        viewModelScope.launch {
            _repos.value = gitHubService.listRepos("jean-t86")
        }
    }
}
