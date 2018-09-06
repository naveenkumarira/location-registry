package com.example.astalos.locationregistry.domain.repository

/**
 * @author Tomasz Czura on 9/4/18.
 */
sealed class OneOf<out E, out S> {
    data class Error<out E>(val error: E) : OneOf<E, Nothing>()
    data class Success<out S>(val data: S) : OneOf<Nothing, S>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <E> error(error: E) = OneOf.Error(error)
    fun <S> success(data: S) = OneOf.Success(data)

    fun oneOf(onError: (E) -> Any, onSuccess: (S) -> Any): Any =
            when (this) {
                is OneOf.Error -> onError(error)
                is OneOf.Success -> onSuccess(data)
            }
}