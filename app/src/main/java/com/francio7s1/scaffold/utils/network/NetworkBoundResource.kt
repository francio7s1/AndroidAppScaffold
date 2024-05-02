package com.francio7s1.scaffold.utils.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

const val authorization = "authorization"

@Suppress("TooGenericExceptionCaught")
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow<AsyncResult<ResultType>> {
    emit(AsyncResult.loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(AsyncResult.loading(data))

        try {
            saveFetchResult(fetch())
            query().map { AsyncResult.success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { AsyncResult.error(throwable, it) }
        }
    } else {
        query().map { AsyncResult.success(it) }
    }

    emitAll(flow)
}
