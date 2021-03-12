package au.com.example.retrofit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import au.com.example.retrofit.api.Repo
import au.com.example.retrofit.api.util.ApiEmptyResponse
import au.com.example.retrofit.api.util.ApiErrorResponse
import au.com.example.retrofit.api.util.ApiSuccessResponse
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getRepos("jean-t86").observe(this, {
            when (it) {
                is ApiSuccessResponse -> processResponse(it.body)
                is ApiEmptyResponse -> emptyResponse()
                is ApiErrorResponse -> errorResponse(it.errorMessage)
            }
        })

        val button = findViewById<Button>(R.id.btnTest)
        button.setOnClickListener {
            mainViewModel.onBtnTestClick()
        }

        mainViewModel.counter.observe(this, {
            findViewById<TextView>(R.id.tvIsBlocking).also { textView ->
                textView.text = it.toString()
            }
        })
    }

    private fun emptyResponse() {
        Timber.d("")
    }

    private fun errorResponse(errorMessage: String) {
        Timber.d("")
    }

    private fun processResponse(repos: List<Repo>) {
        val tvList = findViewById<TextView>(R.id.tvList)
        tvList.text = repos.count().toString()
    }
}
