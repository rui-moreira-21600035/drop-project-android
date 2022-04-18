package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.remote

import android.app.Application
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.dao.AssignmentDao
import retrofit2.Retrofit

class RemoteData(val storage: AssignmentDao, val retrofit: Retrofit, val application: Application) {
}