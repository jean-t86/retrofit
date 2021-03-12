package au.com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import au.com.example.retrofit.util.ApiEmptyResponse
import au.com.example.retrofit.util.ApiErrorResponse
import au.com.example.retrofit.util.ApiSuccessResponse

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTest = findViewById<Button>(R.id.btnTest)
        btnTest.setOnClickListener {
            mainViewModel.repos.observe(this, {
                when (it) {
                    is ApiSuccessResponse -> processResponse(it.body)
                    is ApiEmptyResponse -> hello()
                    is ApiErrorResponse -> world()
                }
                Log.d("", "")
            })
        }
    }

    private fun world() {
        Log.d("", "")
    }

    private fun hello() {
        Log.d("", "")
    }

    private fun processResponse(repos: List<Repo>) {
        val tvList = findViewById<TextView>(R.id.tvList)
        tvList.text = repos.count().toString()
    }
}
