package com.example.farmer.data

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmer.models.Tip
import com.example.farmer.models.UserModel
import com.google.firebase.database.*
import kotlinx.coroutines.launch

class TipsViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val tipsRef: DatabaseReference = db.getReference("tips")
    private val usersRef: DatabaseReference = db.getReference("Users")

    private val _tips = MutableLiveData<List<Tip>>()
    val tips: LiveData<List<Tip>> = _tips

    private val _userNames = mutableStateMapOf<String, String>()
    val userNames: Map<String, String> = _userNames

    init {
        fetchTips()
    }

    fun addTip(tip: Tip) {
        viewModelScope.launch {
            val newTipRef = tipsRef.push()
            val tipWithId = tip.copy(tipId = newTipRef.key ?: "")
            newTipRef.setValue(tipWithId)

        }
    }

    private fun fetchTips() {
        tipsRef.orderByChild("timestamp").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _tips.value = emptyList()
                for (tipSnapshot in snapshot.children) {
                    val tip = tipSnapshot.getValue(Tip::class.java)
                    tip?.let {
                        _tips.value = _tips.value.orEmpty() + it
                        fetchUserName(it.authorId)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if needed
            }
        })
    }

    private fun fetchUserName(uid: String) {
        if (_userNames.containsKey(uid)) return

        usersRef.child(uid).get().addOnSuccessListener { snapshot ->
            val user = snapshot.getValue(UserModel::class.java)
            _userNames[uid] = user?.fullname ?: "Unknown"
        }.addOnFailureListener {
            _userNames[uid] = "Unknown"
        }
    }
    fun deleteTip(tipId :String){
        tipsRef.child(tipId).removeValue().addOnSuccessListener {
            _tips.value = _tips.value?.filter { it.tipId != tipId }
            Log.d("Delete","Tip deleted successfully")
        }.addOnFailureListener{ e ->
            Log.e("Delete","Error deleting tip",e)
        }    }
}
