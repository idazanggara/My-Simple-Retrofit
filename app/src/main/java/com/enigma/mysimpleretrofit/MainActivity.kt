package com.enigma.mysimpleretrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.enigma.mysimpleretrofit.databinding.ActivityMainBinding
import com.enigma.mysimpleretrofit.databinding.DialogLoadingBinding
import com.enigma.mysimpleretrofit.network.RetrofitInstance
import com.enigma.mysimpleretrofit.network.api.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService
    private var loadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        apiService = RetrofitInstance.apiService // ini gson

        binding.apply {
            btnGet.setOnClickListener {
                getUser()
            }
        }

    }

    private fun getUser() {
        CoroutineScope(Dispatchers.Main).launch {
            showLoading("Gettting, Please wait ...")
            val result = apiService.getUser()
            if(result.isSuccessful){
                hideLoading()
                Log.e("Yess, Get Data", "getUser() success ${result.body()?.data}")
            }else {
                hideLoading()
                Log.e("Oh noo, Error i Get Data", "getUser() field: ${result.message()}")
            }
        }
    }

    private fun showLoading(msg: String){
        val dialogBinding = DialogLoadingBinding.inflate(LayoutInflater.from(this))
        dialogBinding.tvLoadingMessage.text = msg

        if (loadingDialog == null) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .setCancelable(false)
                .create()
        }

        loadingDialog?.show()

    }
    private fun hideLoading() {
        loadingDialog?.dismiss()
    }
}