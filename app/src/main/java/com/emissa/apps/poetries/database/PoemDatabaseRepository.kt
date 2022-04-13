package com.emissa.apps.poetries.database

import com.emissa.apps.poetries.model.Poem

interface PoemDatabaseRepository {
    suspend fun insertPoems(newPoetry: List<Poem>)

    suspend fun getPoemByAuthor(author: String): List<Poem>
    suspend fun getPoemByTitle(title: String): List<Poem>
    suspend fun getRandomPoem(): Poem

    suspend fun deletePoems(poems: List<Poem>)
    suspend fun deletePoem(vararg poem: Poem)
}

class PoemDatabaseRepositoryImpl(
    private val poemDao: PoemDao
) : PoemDatabaseRepository {

    override suspend fun insertPoems(newPoetry: List<Poem>) {
        poemDao.insertPoems(newPoetry)
    }


    override suspend fun getPoemByAuthor(author: String): List<Poem> {
        return poemDao.getPoemByAuthor(author)
    }

    override suspend fun getPoemByTitle(title: String): List<Poem> {
        return poemDao.getPoemByTitle(title)
    }

    override suspend fun getRandomPoem(): Poem {
        return poemDao.getRandomPoem()
    }

    override suspend fun deletePoems(poems: List<Poem>) {
        return poemDao.deletePoems(poems)
    }

    override suspend fun deletePoem(vararg poem: Poem) {
        return poemDao.deletePoem(*poem)
    }

}