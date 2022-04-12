package com.emissa.apps.poetries.network

import com.emissa.apps.poetries.model.Authors
import com.emissa.apps.poetries.model.Poem
import retrofit2.Response


interface PoetryRepository {
    suspend fun getAllAuthors() : Response<Authors>
    suspend fun getAllPoemByTitle() : Response<List<Poem>>
    suspend fun getPoemByAuthor(authorName: String) : Response<List<Poem>>
    suspend fun getPoemByTitle(poemTitle: String) : Response<List<Poem>>
    suspend fun getRandomPoem() : Response<Poem>
    suspend fun getMultipleRandomPoem(randCount: Int) : Response<List<Poem>>
    suspend fun getPoemByLineCount(lineCount: Int) : Response<List<Poem>>
}


class PoetryRepositoryImpl(
    private val poetryService: PoetryService
): PoetryRepository {
    override suspend fun getAllAuthors(): Response<Authors> =
        poetryService.getAllAuthors()

    override suspend fun getAllPoemByTitle(): Response<List<Poem>> =
        poetryService.getAllPoemByTitle()

    override suspend fun getPoemByAuthor(authorName: String): Response<List<Poem>> =
        poetryService.getPoemByAuthor(authorName)

    override suspend fun getPoemByTitle(poemTitle: String): Response<List<Poem>> =
        poetryService.getPoemByTitle(poemTitle)

    override suspend fun getRandomPoem(): Response<Poem> =
        poetryService.getRandomPoem()

    override suspend fun getMultipleRandomPoem(randCount: Int): Response<List<Poem>> =
        poetryService.getMultipleRandomPoem(randCount)

    override suspend fun getPoemByLineCount(lineCount: Int): Response<List<Poem>> =
        poetryService.getPoemByLineCount(lineCount)
}
