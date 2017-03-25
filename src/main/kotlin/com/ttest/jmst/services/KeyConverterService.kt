package com.ttest.jmst.services

/**
 * Created by bajga on 20.03.2017.
 */
interface KeyConverterService {
    fun  idToKey(id: Long): String
    fun keyToId(key: String): Long
}