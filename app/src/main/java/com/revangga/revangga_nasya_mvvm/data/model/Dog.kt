package com.revangga.revangga_nasya_mvvm.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "dogs")
@Parcelize
data class Dog(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "url") var url: String
) : Parcelable
