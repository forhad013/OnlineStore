package com.greenrobotdev.onlinestore.data.repository

import com.greenrobotdev.onlinestore.data.base.Response
import com.greenrobotdev.onlinestore.data.base.getResponse
import com.greenrobotdev.onlinestore.domain.entity.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal fun  singleSourceOfTruth (
    getLocalData: suspend () -> List<Product>,
    getRemoteData: suspend () -> List<Product>,
    saveDataToLocal: suspend (List<Product>) -> Unit,
): Flow<Response<List<Product>>> = flow {
    val localData = getResponse { getLocalData() }
    if (localData is Response.Success && localData.data.isNotEmpty()) {
        emit(localData)
    } else {
        val remoteData = getResponse { getRemoteData() }
        if (remoteData is Response.Success) {
            if (remoteData.data.isNotEmpty()) {
                saveDataToLocal(remoteData.data)
                val localDataUpdated = getResponse { getLocalData() }
                emit(localDataUpdated)
            }
        } else {
            emit(Response.Error("Error", (remoteData as Response.Error).throwable))
        }
    }
}