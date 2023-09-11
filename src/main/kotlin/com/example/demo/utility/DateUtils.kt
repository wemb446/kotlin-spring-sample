package com.example.demo.utility

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class DateUtils {

    companion object {
        val DEFAULT_LOCAL_DATETIME: LocalDateTime =
            LocalDateTime.ofEpochSecond(0L, 0, ZoneOffset.UTC)
                .toInstant(ZoneOffset.UTC)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
    }

}