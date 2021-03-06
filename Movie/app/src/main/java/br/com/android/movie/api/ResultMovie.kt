package br.com.android.movie.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultMovie(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable