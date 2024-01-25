package com.jonathansteele.news

import kotlinx.datetime.Instant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSDateFormatterShortStyle
import platform.Foundation.NSTimeZone
import platform.Foundation.systemTimeZone

private val mediumDateFormatter by lazy {
    val formatter = NSDateFormatter()
    formatter.dateStyle = NSDateFormatterMediumStyle
    formatter.timeStyle = NSDateFormatterShortStyle
    formatter.timeZone = NSTimeZone.systemTimeZone
    formatter
}

actual fun formattingDate(instant: Instant): String {
    return mediumDateFormatter.stringFromDate(instant.toNSDate())
}
