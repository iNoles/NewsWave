package com.jonathansteele.news

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private val mediumDateFormatter by lazy {
    DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
        .withZone(ZoneId.systemDefault())
}

actual fun formattingDate(instant: Instant): String {
    return mediumDateFormatter.format(instant.toJavaInstant())
}
