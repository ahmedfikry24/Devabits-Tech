package com.example.devabitstech.domain.exceptions


abstract class AppException(message: String, cause: Throwable? = null) : Exception(message, cause)

class UnknownException(
    message: String = ErrorMessageConstants.UNKNOWN_ERROR,
    cause: Throwable? = null
) : AppException(message, cause)


class NoCachedPrayersException(
    message: String = ErrorMessageConstants.OFFLINE_MODE_EXCEPTION,
    cause: Throwable? = null
) : AppException(message, cause)