package com.ryancphil.pokedex.data.paging

interface Paginator<Key, Item> {
    suspend fun loadNextPage()
    fun reset()
}