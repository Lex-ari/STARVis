package com.broncospace.android.starvis

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.broncospace.android.starvis.api.N2YOApi
import com.broncospace.android.starvis.api.PositionInterceptor
import com.broncospace.android.starvis.api.PositionItem
import com.broncospace.android.starvis.spacecraft.SpacecraftDatabase
import com.broncospace.android.starvis.spacecraft.SpacecraftItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.lang.IllegalStateException
import java.util.UUID

private const val DATABASE_NAME = "spacecraft-database"
class SatelliteRepository private constructor(context: Context) {
    private val n2yoApi: N2YOApi
    //private val spacecraft: List<SpacecraftItem>

    private val database: SpacecraftDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            SpacecraftDatabase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PositionInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.n2yo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            //.client(okHttpClient)
            .build()
        n2yoApi = retrofit.create()
        startIsolatedFetch()
    }

    companion object {
        private var INSTANCE: SatelliteRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = SatelliteRepository(context)
            }
        }

        fun get(): SatelliteRepository {
            return INSTANCE ?: throw IllegalStateException("SatelliteRepository must be initialized")
        }
    }

    private var job: Job? = null
    private var fetching = false
    private var latestData = PositionItem(0F,0F,0F,0F,0F,0F,0F, 0F)
    fun startIsolatedFetch() {
        fetching = true
        job = CoroutineScope(Dispatchers.Default).launch {
            while (fetching) {
                latestData = fetchSatellites()[0]
                delay(1000L)
                Log.d("SatelliteRepository", "Got new latest data: ${latestData}")
            }
        }
    }

    fun getLatestData() : PositionItem {
        return latestData
    }

    suspend fun fetchSatellites() : List<PositionItem> =
        n2yoApi.fetchSatellites().positions

    suspend fun getSpacecraft() : List<SpacecraftItem> = database.spacecraftDao().getSpacecraft()

    suspend fun getSpacecraft(id: Integer) : SpacecraftItem = database.spacecraftDao().getSpacecraft(id)
}