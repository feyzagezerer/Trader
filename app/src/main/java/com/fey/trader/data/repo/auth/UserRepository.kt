package com.fey.trader.data.repo.auth

import android.content.SharedPreferences
import com.fey.trader.data.remote.MatriksApi
import com.fey.trader.utils.UserAuthResult
import retrofit2.HttpException
import javax.inject.Inject

class UserRepository @Inject constructor(
private val matriksApi: MatriksApi,   private val prefs: SharedPreferences
) {

    suspend fun login(
        username: String,
        password: String
    ): UserAuthResult<Unit>{ return try {
        val response = matriksApi.getUserData(
            request = UserRequest(
                username = username,
                password = password
            )
        )
        prefs.edit()
            .putString("jwt",response.AccountID)
            .apply()
        UserAuthResult.UserAuthorized()
    } catch (e: HttpException) {
        if(e.code() == 401){
            UserAuthResult.UserUnAuthorized()
        }
        else{
            UserAuthResult.UserUnknownError()
        }
    } catch (e: Exception){
        UserAuthResult.UserUnknownError()
    }}

    suspend fun getUserData(AccountID: String, username: String,password: String,ExchangeID: String,OutputType: String) {
       // matriksApi.login(AccountID,username, password,ExchangeID,OutputType)
    }

    suspend fun signOut(): UserAuthResult<Unit>{
        return try{
            prefs.edit().clear().apply()
            UserAuthResult.UserUnAuthorized()
        }catch (e: Exception){
            UserAuthResult.UserUnknownError()
        }
    }
}