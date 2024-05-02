package com.francio7s1.scaffold.utils.network

data class AsyncResult<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data: T?): AsyncResult<T> {
            return AsyncResult(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable, data: T?): AsyncResult<T> {
            return AsyncResult(Status.ERROR, data, throwable)
        }

        fun <T> loading(data: T?): AsyncResult<T> {
            return AsyncResult(Status.LOADING, data, null)
        }
    }
}

