package com.example.farmer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

import android.util.Log
import com.example.farmer.models.Crops
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CropsViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val _crops = MutableLiveData<List<Crops>>()
    val crops: LiveData<List<Crops>>  = _crops

    private var listenerRegistration: ListenerRegistration? = null

    init {
        fetchCrops()
    }

    private fun fetchCrops() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val crops = mutableListOf<Crops>()
                for (data in snapshot.children) {
                    val crop = data.getValue(Crops::class.java)
                    crop?.let { crops.add(it) }
                }
                _crops.value = crops
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("CropViewModel", "Failed to fetch crops: ${error.message}")
            }
        })
    }
    private val database = FirebaseDatabase.getInstance().getReference("crops")

    fun addCrop(crop: Crops, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val cropId = database.push().key
        cropId?.let {
            database.child(it).setValue(crop)
                .addOnSuccessListener {

                    onSuccess()
                }
                .addOnFailureListener { exception ->
                   
                    onFailure(exception.message ?: "Unknown error")
                }
        } ?: onFailure("Failed to generate crop ID")
    }
    fun deleteCrop(id: String) {
        firestore.collection("crops").document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("CropsViewModel", "Crop deleted successfully!")
            }
            .addOnFailureListener { e ->
                Log.e("CropsViewModel", "Error deleting crop: ", e)
            }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}
