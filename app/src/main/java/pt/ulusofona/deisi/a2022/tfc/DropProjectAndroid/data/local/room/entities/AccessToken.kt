package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "accesstokens")
data class AccessToken(
    @PrimaryKey val user: String,
    val token: String
)
