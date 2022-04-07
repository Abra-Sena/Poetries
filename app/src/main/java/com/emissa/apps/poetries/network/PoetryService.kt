package com.emissa.apps.poetries.network

import com.emissa.apps.poetries.model.Authors
import com.emissa.apps.poetries.model.Poem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PoetryService {

    /**
     * Retrieving all Authors from the poetry database
     */
    @GET(AUTHOR_PATH)
    suspend fun getAllAuthors(): Response<Authors>

    /**
     * Search for an author with their name /or part of their name
     */
    @GET("$AUTHOR_PATH/<author>")
    suspend fun getPoemByAuthor(
        @Query("author") authorName: String
    ) : Response<List<Poem>>

    /**
     * Retrieve a poem by searching the title
     */
    @GET(TITLE_PATH)
    suspend fun getPoemByTitle()

    /**
     * Get a random poem
     */
    @GET(RANDOM_PATH)
    suspend fun getRandomPoem()

    /**
     * Get a certain number of random poem
     */
    @GET("$RANDOM_PATH/<random count>")
    suspend fun getMultipleRandomPoem(
        @Query("random count") randCount: Int
    ) : Response<List<Poem>>

    /**
     * Get poem with text that have equals line count as the param
     */
    @GET(LINE_COUNT_PATH)
    suspend fun getPoemByLineCount(
        @Query("linecount") lineCount: Int
    ) : Response<List<Poem>>


    companion object {
        const val BASE_URL = "https://poetrydb.org/"
        // retrieves all authors alphabetically ordered
        private const val AUTHOR_PATH = "author"
        // retrieves a random poem
        private const val RANDOM_PATH = "random"
        // retrieves all poetry alphabetically ordered by title
        private const val TITLE_PATH = "title"
        // retrieves poetry with the specified line count
        private const val LINE_COUNT_PATH = "linecount/<linecount>"
    }
}