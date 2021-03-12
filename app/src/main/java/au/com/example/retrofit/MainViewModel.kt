package au.com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.example.retrofit.util.ApiResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var repos: LiveData<ApiResponse<List<Repo>>> = MutableLiveData()

    init {
        val gitHubService = getGitHubService()
        viewModelScope.launch {
            repos = gitHubService.listRepos("jean-t86")
        }
    }
}
