package com.example.manejobugs.data.network

import com.example.manejobugs.data.dto.BugDto
import com.example.manejobugs.data.dto.CreateBugRequestDto
import com.example.manejobugs.data.dto.LoginRequestDto
import com.example.manejobugs.data.dto.LoginResponseDto
import com.example.manejobugs.data.dto.UpdateBugStatusRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service interface.
 * Endpoint paths match the API contract defined in /contracts/bugs-api.yaml.
 *
 * To connect a real backend: replace MockBugRepository with RetrofitBugRepository
 * and provide a real Retrofit instance via NetworkModule. No other changes needed.
 */
interface BugApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    @GET("bugs")
    suspend fun getBugs(
        @Query("status") status: String? = null,
        @Query("priority") priority: String? = null,
        @Query("severity") severity: String? = null
    ): List<BugDto>

    @GET("bugs/{id}")
    suspend fun getBugById(@Path("id") id: String): BugDto

    @POST("bugs")
    suspend fun createBug(@Body request: CreateBugRequestDto): BugDto

    @PATCH("bugs/{id}/status")
    suspend fun updateBugStatus(
        @Path("id") id: String,
        @Body request: UpdateBugStatusRequestDto
    ): BugDto
}
