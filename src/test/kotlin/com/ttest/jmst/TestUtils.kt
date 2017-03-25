package com.ttest.jmst

import org.mockito.Mockito

/**
 * Created by bajga on 24.03.2017.
 */
fun<T> whenever(call: T) = Mockito.`when`(call)