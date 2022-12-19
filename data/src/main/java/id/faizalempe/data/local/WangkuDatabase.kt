package id.faizalempe.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.faizalempe.data.BuildConfig
import id.faizalempe.data.local.dao.TransactionDao
import id.faizalempe.data.local.model.entity.TransactionEntity

/**
 * @author Faizal Muhammad Priyowivowo (faizal.priyowibowo@dana.id)
 * @version WangkuDatabase, v 0.1 12/12/22 16.35 by Faizal Muhammad Priyowivowo
 */
@Database(
    entities = [TransactionEntity::class],
    version = 1
)
abstract class WangkuDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: WangkuDatabase? = null

        fun getInstance(context: Context): WangkuDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private const val DATABASE_NAME = BuildConfig.DB_NAME

        private fun buildDatabase(context: Context): WangkuDatabase {
            return Room.databaseBuilder(context, WangkuDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration() // TODO (Faizal): Remove this if go to prod
                .build()
        }
    }

}