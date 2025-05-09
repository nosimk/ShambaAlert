package com.example.farmer.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.farmer.api.WeatherApi
import com.example.farmer.api.WeatherModel
import com.example.farmer.api.RetrofitInstance
import com.example.farmer.api.Constant
import com.example.farmer.api.NetworkResponse

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>> ()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult
    fun getData(city:String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch{
            try {
                val response : Response<WeatherModel> = weatherApi.getWeather(Constant.apiKey , city)
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }

                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load Data")

                }
            }
            catch ( e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load Data")
            }

        }


    }
}