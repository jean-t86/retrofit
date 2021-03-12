package au.com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import au.com.example.retrofit.api.Repo
import au.com.example.retrofit.api.getGitHubService
import au.com.example.retrofit.api.util.ApiResponse

class MainViewModel : ViewModel() {

    private val gitHubService = getGitHubService()

    fun getRepos(user: String): LiveData<ApiResponse<List<Repo>>> = liveData {
        emit(gitHubService.listRepos(user))
    }

    fun onBtnTestClick() {
        _counter.value = _counter.value?.plus(1)
    }

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter
}
