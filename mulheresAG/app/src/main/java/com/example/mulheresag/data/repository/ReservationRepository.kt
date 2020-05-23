package com.example.mulheresag.data.repository

import com.example.mulheresag.data.remote.ReservationAPI
import com.example.mulheresag.data.remote.ServicesAPI
import com.example.mulheresag.data.remote.model.ReservationModel
import com.example.mulheresag.data.remote.model.ServiceModel
import com.example.mulheresag.domain.reservation.ReservationContract
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.BaseCallBack
import com.example.mulheresag.infra.Repository
import com.example.mulheresag.infra.formatResponseError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationRepository : Repository(), ReservationContract.IRepository {

    override fun getReservation(id:Int,onResult: BaseCallBack<ArrayList<ReservationModel>>) {
        super.data.restApi(ReservationAPI::class.java)
            .getReservation(id,App.userToken)
            .enqueue(object : Callback<ArrayList<ReservationModel>> {
                override fun onFailure(call: Call<ArrayList<ReservationModel>>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<ReservationModel>>,
                    response: Response<ArrayList<ReservationModel>>
                ) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }

    override fun createReservation(
        model: ReservationModel,
        onResult: BaseCallBack<ReservationModel>
    ) {
        super.data.restApi(ReservationAPI::class.java)
            .createReservation(App.userToken, model)
            .enqueue(object : Callback<ReservationModel> {
                override fun onFailure(call: Call<ReservationModel>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ReservationModel>,
                    response: Response<ReservationModel>
                ) {
                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })

    }

    override fun getAllServices(onResult: BaseCallBack<ArrayList<ServiceModel>>) {
        super.data.restApi(ServicesAPI::class.java)
            .getAllServices(App.userToken)
            .enqueue(object : Callback<ArrayList<ServiceModel>> {
                override fun onFailure(call: Call<ArrayList<ServiceModel>>, t: Throwable) {
                    onResult.onUnsuccessful(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<ServiceModel>>,
                    response: Response<ArrayList<ServiceModel>>
                ) {
                    if (!response.isSuccessful || response.code() != 200) {
                        var error =
                            formatResponseError(response.errorBody()?.string())
                        onResult.onUnsuccessful(error.getString("error"))
                    }

                    response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }


}