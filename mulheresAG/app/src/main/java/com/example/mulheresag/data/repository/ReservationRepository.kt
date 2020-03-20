package com.example.mulheresag.data.repository

import com.example.mulheresag.data.remote.ReservationAPI
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.domain.reservation.ReservationContract
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationRepository : Repository(), ReservationContract.IRepository {
    override fun getReservation(onResult: BaseCallBack<ArrayList< ReservationModel>>) {
        super.data.restApi(ReservationAPI::class.java)
            .getReservation(App.userToken)
            .enqueue(object : Callback<ArrayList< ReservationModel>> {
                override fun onFailure(call: Call<ArrayList< ReservationModel>>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(call: Call<ArrayList< ReservationModel>>, response: Response<ArrayList< ReservationModel>>) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }
}