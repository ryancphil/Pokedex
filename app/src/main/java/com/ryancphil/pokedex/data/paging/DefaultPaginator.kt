package com.ryancphil.pokedex.data.paging

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Result<Item?>,
    private val getNextKey: suspend (Key) -> Key,
    private val onError: suspend (Throwable?) -> Unit,
    private val onSuccess: suspend (result: Item, newKey: Key) -> Unit,
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val response = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        response?.let {
            currentKey = getNextKey(currentKey)
            onSuccess(response, currentKey)
            onLoadUpdated(false)
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}