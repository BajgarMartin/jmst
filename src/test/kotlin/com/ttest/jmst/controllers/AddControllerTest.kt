package com.ttest.jmst.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ttest.jmst.services.KeyMapperService
import com.ttest.jmst.whenever
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import org.springframework.web.context.WebApplicationContext

/**
 * Created by bajga on 24.03.2017.
 */
@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddControllerTest {
    @Autowired lateinit var webApplicationContext : WebApplicationContext

    lateinit var mockMVC: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: AddController

    private val LINK: String = "Link"

    private val KEY: String = "key"

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMVC = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()

        whenever(service.add(LINK)).thenReturn(KEY)
    }

    @Test
    fun wheneverAddLinkTakesAKey() {
        mockMVC.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(AddController.AddRequest(LINK))))
        .andExpect(MockMvcResultMatchers.jsonPath("$.key", Matchers.equalTo(KEY)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.link", Matchers.equalTo(LINK)))
    }

    @Test fun whenUserAddLinkByFormHeTakesAWebPage() {
        mockMVC.perform(MockMvcRequestBuilders.post("/addhtml")
                .param("link", LINK)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(KEY)))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(LINK)))
    }
}