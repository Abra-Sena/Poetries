package com.emissa.apps.poetries.network

import com.emissa.apps.poetries.model.Poem
import retrofit2.Response


interface PoetryRepository {
    suspend fun getAllAuthors() : Response<List<String>>
    suspend fun getAllPoemByTitle() : Response<List<String>>
    suspend fun getPoemByAuthor(authorName: String) : Response<List<Poem>>
    suspend fun getPoemByTitle(poemTitle: String) : Response<List<Poem>>
    suspend fun getRandomPoem() : Response<Poem>
}


class PoetryRepositoryImpl(
    private val poetryService: PoetryService
): PoetryRepository {
    override suspend fun getAllAuthors(): Response<List<String>> =
        poetryService.getAllAuthors()

    override suspend fun getAllPoemByTitle(): Response<List<String>> =
        poetryService.getAllPoemByTitle()

    override suspend fun getPoemByAuthor(authorName: String): Response<List<Poem>> =
        poetryService.getPoemByAuthor(authorName)
//
    override suspend fun getPoemByTitle(poemTitle: String): Response<List<Poem>> =
        poetryService.getPoemByTitle(poemTitle)

    override suspend fun getRandomPoem(): Response<Poem> =
        poetryService.getRandomPoem()
}
