package com.enigma.mysimpleretrofit.network.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.squareup.moshi.Json

@Parcelize
data class BaseResponseMoshi(

	@Json(name="data")
	val data: Data? = null,

) : Parcelable

@Parcelize
data class Data(
	@Json(name = "id")
	val id: Int? = 0,

	@Json(name = "email")
	val email: String? = "",

	@Json(name = "first_name")
	val firstName: String? = "",

	@Json(name = "last_name")
	val lastName: String? = "",

	@Json(name = "avatar")
	val avatar: String? = ""
) : Parcelable
