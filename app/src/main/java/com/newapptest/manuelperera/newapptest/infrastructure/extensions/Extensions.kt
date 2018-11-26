package com.newapptest.manuelperera.newapptest.infrastructure.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "yyyy-MM-dd"): String =
    SimpleDateFormat(pattern, Locale.US).format(this)

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? =
    if (p1 != null && p2 != null) block(p1, p2) else null