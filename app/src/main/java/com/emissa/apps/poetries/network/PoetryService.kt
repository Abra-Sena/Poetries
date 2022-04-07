package com.emissa.apps.poetries.network

interface PoetryService {



    companion object {
        const val BASE_URL = "https://poetrydb.org/"
        // retrieves all authors alphabetically ordered
        private const val AUTHOR_PATH = "author"
        // retrieves a random poem
        private const val RANDOM_PATH = "random"
        // retrieves all poetry alphabetically ordered by title
        private const val TITLE_PATH = "title"
        // retrieves poetry with the specified line count
        private const val LINE_COUNT_PATH = 1
    }
}