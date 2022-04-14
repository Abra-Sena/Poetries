package com.emissa.apps.poetries.database

import androidx.room.*
import com.emissa.apps.poetries.model.Poem
import com.emissa.apps.poetries.utils.DataConverter


@Database(entities = [Poem::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class PoetryDatabase : RoomDatabase() {
    abstract fun getPoemDao() : PoemDao
}


@Dao
interface PoemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoems(newPoetry: List<Poem>)


    @Query("SELECT * FROM poem WHERE author = :author" )
    suspend fun getPoemByAuthor(author: String): List<Poem>
    @Query("SELECT * FROM poem WHERE title = :title" )
    suspend fun getPoemByTitle(title: String): List<Poem>
    @Query("SELECT * FROM poem" )
    suspend fun getRandomPoem(): Poem

    @Delete
    suspend fun deletePoems(poems: List<Poem>)
    @Delete
    suspend fun deletePoem(vararg poem: Poem)
}