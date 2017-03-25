package com.ttest.jmst.controllers

import com.ttest.jmst.JmstApplication
import com.ttest.jmst.services.KeyMapperService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * Created by bajga on 15.03.2017.
 */
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedirectControllerTest {

    @Autowired lateinit var webApplicationContext : WebApplicationContext

    lateinit var mockMVC: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMVC = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()

        Mockito.`when`(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.NotFound(BAD_PATH))
    }

    private val PATH = "aAbBcCdD"

    private val REDIRECT_STATUS: Int = 302

    private val HEADER_NAME = "Location"

    private val HEADER_VALUE = "http://www.eveonline.com"

    @Test fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        mockMVC.perform (get("/$PATH"))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_PATH = "olololo"

    private val NOT_FOUND : Int = 404
    @Test fun controllerMustReturn404IfBadKey() {
        mockMVC.perform(get("/$BAD_PATH"))
                .andExpect { status().`is`(NOT_FOUND) }
    }

    @Test fun homeWorksFine() {
        mockMVC.perform (get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
    }
}