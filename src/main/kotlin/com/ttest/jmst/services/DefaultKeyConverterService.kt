package com.ttest.jmst.services

import org.springframework.stereotype.Component

/**
 * Created by bajga on 20.03.2017.
 */
@Component
class DefaultKeyConverterService : KeyConverterService {

    val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray()
    val charToLong = (0..chars.size-1)
            .map { i -> Pair(chars[i], i.toLong())}
            .toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()
        while (n != 0L) {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }

        return builder.reverse().toString()
    }

    override fun keyToId(key: String) = key
            .map { c -> charToLong[c]!! }
            .fold(0L, {a,b -> a * chars.size + b})
}