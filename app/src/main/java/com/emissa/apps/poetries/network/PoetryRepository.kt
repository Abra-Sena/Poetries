package com.emissa.apps.poetries.network

import com.emissa.apps.poetries.model.Poem
import retrofit2.Response


interface PoetryRepository {
    suspend fun getPoemByAuthor(authorName: String) : Response<List<List<Poem>>>
    suspend fun getPoemByTitle(poemTitle: String) : Response<List<List<Poem>>>
    suspend fun getRandomPoem(): Response<List<Poem>>
}


class PoetryRepositoryImpl(
    private val poetryService: PoetryService
): PoetryRepository {

    override suspend fun getPoemByAuthor(authorName: String): Response<List<List<Poem>>> =
        poetryService.getPoemByAuthor(authorName)
//
    override suspend fun getPoemByTitle(poemTitle: String): Response<List<List<Poem>>> =
        poetryService.getPoemByTitle(poemTitle)

    override suspend fun getRandomPoem(): Response<List<Poem>> =
        poetryService.getRandomPoem()
}
