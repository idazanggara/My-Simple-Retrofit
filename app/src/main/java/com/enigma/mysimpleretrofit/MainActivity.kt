package com.enigma.mysimpleretrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.enigma.mysimpleretrofit.databinding.ActivityMainBinding
import com.enigma.mysimpleretrofit.databinding.DialogLoadingBinding
import com.enigma.mysimpleretrofit.network.RetrofitInstance
import com.enigma.mysimpleretrofit.network.api.ApiService
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiService: ApiService
    private var loadingDialog: AlertDialog? = null
    private lateinit var apiServiceMoshi: ApiService

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
        apiServiceMoshi = RetrofitInstance.apiServiceMoshi // ini Moshi

        binding.apply {
            btnGet.setOnClickListener {
                getUser()
            }
            btnGetById.setOnClickListener {
                getUserByID()
            }
            btnUpdate.setOnClickListener {
                updateUser()
            }
            btnDelete.setOnClickListener {
                deteleUser()
            }
            btnPost.setOnClickListener {
                createUser()
            }
        }

    }

    private fun createUser() {
        lifecycleScope.launch {
            showLoading("Post, Please wait....")
            val name = "Anggara"
            val job = "JavaScript Developer"
            val body = JsonObject().apply {
                addProperty("name", name)
                addProperty("job", job)
            }
            val result = apiService.createUser(body)
            if (result.isSuccessful){
                Log.e("Yeay Post Data","createUser success: ${result.body()}")
            } else {
                Log.e("Oh noo, error in Post Data","createUser field: ${result.message()}")
            }
            hideLoading()
        }
    }

    private fun deteleUser() {
        lifecycleScope.launch {
            showLoading("Deleting, Please wait ....")
            val id= "2"
            val result = apiService.deleteUser(id)
            if (result.isSuccessful){
                Log.e("Yeay Delete Data","deleteUser success: ${result.body()}")
            } else {
                Log.e("Oh noo, error in Delete Data","deleteUser field: ${result.message()}")
            }
            hideLoading()
        }
    }

    private fun updateUser() {
        lifecycleScope.launch {
            showLoading("Updating, Please wait ....")
            // kedua data ini bisa dibuat dinamis dari inputan
            val name = " Idaz Coding Delivery"
            val job = "Mobile Developer"
            val body = JsonObject().apply {
                addProperty("name", name)
                addProperty("job", job)
            }
            val id = "2"

            val result = apiService.updateUser(id,body)
            if (result.isSuccessful){
                hideLoading()
                Log.e("Yeay Update Data", "updateUser() success: ${result.body()}")
            } else {
                hideLoading()
                Log.e("Oh noo, error in Update Data", "updateUser() field: ${result.message()}")
            }

        }
    }

    private fun getUserByID() {
        // bisa pakai CoroutineScope atau ini
        lifecycleScope.launch {
            showLoading("Getting by ID, Please wait ....")
            val id = "2" // ini bisa dinamis dari inputan
            val result = apiServiceMoshi.getUserByID(id)
            if(result.isSuccessful){
                hideLoading()
                Log.e("Yess, Get Data", "getUserByID() success ${result.body()?.data}")
            }else {
                hideLoading()
                Log.e("Oh noo, Error in Get Data", "getUserByID() field: ${result.message()}")
            }

        }
    }

    private fun getUser() {
        CoroutineScope(Dispatchers.Main).launch {
            showLoading("Getting, Please wait ....")
            val result = apiService.getUser()
            if(result.isSuccessful){
                hideLoading()
                Log.e("Yess, Get Data", "getUser() success ${result.body()?.data}")
            }else {
                hideLoading()
                Log.e("Oh noo, Error in Get Data", "getUser() field: ${result.message()}")
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