package com.rickandmorty.app.mappers

import com.rickandmorty.app.screens.error.ErrorEntry
import com.rickandmorty.domain.Error

fun Error.toEntry(): ErrorEntry = when (this) {
    is Error.Custom -> ErrorEntry.Custom(
        title = title,
        detail = detail
    )

    Error.NullParams -> ErrorEntry.NullParams
    else -> ErrorEntry.Unknown
}

fun ErrorEntry.toDomain(): Error = when (this) {
    is ErrorEntry.Custom -> Error.Custom(
        title = title,
        detail = detail
    )

    ErrorEntry.NullParams -> Error.NullParams
    else -> Error.Unknown
}