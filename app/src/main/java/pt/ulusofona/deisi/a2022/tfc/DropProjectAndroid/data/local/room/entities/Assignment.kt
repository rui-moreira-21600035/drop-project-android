package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignments")
data class Assignment(
    @PrimaryKey val id: String,
    val title: String,
    val tags: String,
    val subject: String,
    val packageName: String,
    val gitRepoURL: String,
    val programmingLang: String,
    val leaderboardType: Int,
    val dateCreated: String,
    val dateLastModified: String)