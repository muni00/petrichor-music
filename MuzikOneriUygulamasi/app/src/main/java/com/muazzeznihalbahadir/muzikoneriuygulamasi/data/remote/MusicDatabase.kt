package com.muazzeznihalbahadir.muzikoneriuygulamasi.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.muzikoneriuygulamasi.data.entities.Song
import com.muazzeznihalbahadir.muzikoneriuygulamasi.other.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs():List<Song>{
        return try{
            songCollection.get().await().toObjects(Song::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }
}