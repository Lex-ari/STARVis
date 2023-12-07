package com.broncospace.android.starvis.spacecraft

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class SpacecraftItem(
    @PrimaryKey
    val noradId: Integer,
    val name: String,
    val photoLink: String,
)