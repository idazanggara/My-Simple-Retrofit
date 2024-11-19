package com.enigma.mysimpleretrofit.network.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class BaseResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("last_name")
	val lastName: String? = "",

	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("avatar")
	val avatar: String? = "",

	@field:SerializedName("first_name")
	val firstName: String? = "",

	@field:SerializedName("email")
	val email: String? = ""
) : Parcelable


