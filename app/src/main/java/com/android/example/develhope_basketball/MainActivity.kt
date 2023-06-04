import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.example.develhope_basketball.ApiClient
import com.android.example.develhope_basketball.ApiResponse
import com.android.example.develhope_basketball.ApiService
import com.android.example.develhope_basketball.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val apiKey = "5a6bfee21amsh44b6c0d7949fbb4p19510cjsn421f7bcb02dd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = ApiClient.create(apiKey).create(ApiService::class.java)
        doWorld()
    }

    private fun doWorld() {
        coroutineScope.launch {
            val response: Response<ApiResponse> = apiService.fetchData()

            if (response.isSuccessful) {
                val apiResponse: ApiResponse? = response.body()
                Log.d("API", "Success: ${response.isSuccessful}")
            } else {
                Log.e("API", "Error: ${response.errorBody()}")
            }
        }
    }
}