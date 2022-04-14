package com.emissa.apps.poetries.network

import com.emissa.apps.poetries.model.Poem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PoetryService {
    companion object {
        const val BASE_URL = "https://poetrydb.org/"
        // retrieves all authors having the passed param in the name
        private const val AUTHOR_PATH = "author"
        // retrieves all poem having the passed param in the title
        private const val TITLE_PATH = "title"
        // retrieves a random poem
        private const val RANDOM_PATH = "random"
    }

    /**
     * Search for an author with their name /or part of their name equal the passed param
     */
    @GET("$AUTHOR_PATH/author")
    suspend fun getPoemByAuthor(
        @Query("author") authorName: String
    ) : Response<List<List<Poem>>>

    /**
     * Search for poems that have the passed param string in their title
     */
    @GET("$TITLE_PATH/title")
    suspend fun getPoemByTitle(
        @Query("title") poemTitle: String
    ) : Response<List<List<Poem>>>


    /**
     * Get a random poem
     */
    @GET(RANDOM_PATH)
    suspend fun getRandomPoem() : Response<List<Poem>>


    /**
     * Retrieving all Authors from the poetry database
     */
    @GET(AUTHOR_PATH)
    suspend fun getAllAuthors(): Response<List<String>>

    /**
     * Retrieve a poem by searching the title
     */
    @GET(TITLE_PATH)
    suspend fun getAllPoemByTitle() : Response<List<String>>

}