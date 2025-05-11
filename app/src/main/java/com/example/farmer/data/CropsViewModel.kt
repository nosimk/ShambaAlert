package com.example.farmer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.farmer.models.Crops
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CropsViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val db = FirebaseFirestore.getInstance()


    private val _crops = MutableStateFlow<List<Crops>>(emptyList())
    val crops: StateFlow<List<Crops>> = _crops





    private var listenerRegistration: ListenerRegistration? = null
    init {
        fetchCrops ()
    }


    private fun fetchCrops(){
        Firebase.database.reference.child("crops").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val cropList = snapshot.children.mapNotNull { it.getValue(Crops::class.java) }
                _crops.value = cropList
                Log.d("RTDB","Fetched ${cropList.size} crops")

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("RTDB","Error fetching crops :${error.message}")
            }
        })    }


    fun saveCropToDatabase(
        crop: Crops,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("crops")
            .add(crop)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e.message ?: "Error occurred") }
    }

    fun addCrop(
        crop: Crops,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        Firebase.database.reference
            .child("crops")
            .child(crop.cropId)
            .setValue(crop)
            .addOnSuccessListener {
                Log.d("RTDB", "Crop added succesfully!")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("RTDB", "Error adding crop", e)
                onFailure(e.message ?: "Unknown Error")
            }


    }}
//    fun deleteCrop(cropId :String){
//        cropsRef.child(cropId).removeValue().addOnSuccessListener {
//            _crops.value = _crops.value?.filter { it.cropId != cropId }
//            Log.d("Delete","Tip deleted successfully")
//        }.addOnFailureListener{ e ->
//            Log.e("Delete","Error deleting tip",e)
//        }    }}



//    fun deleteCrop(id: String) {
//        firestore.collection("crops").document(id)
//            .delete()
//            .addOnSuccessListener {
//                Log.d("CropsViewModel", "Crop deleted successfully!"
//            })
//            }
//            .addOnFailureListener { e ->
//                Log.e("CropsViewModel", "Error deleting crop: ", e)
//            }
//
//    override fun onCleared() {
//        super.onCleared()
//        listenerRegistration?.remove()
//    }
//}
