package com.ttest.jmst.controllers

import com.ttest.jmst.services.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.net.HttpCookie
import javax.naming.ldap.ExtendedResponse
import javax.servlet.http.HttpServletResponse

/**
 * Created by bajga on 16.03.2017.
 */
@Controller
@RequestMapping
class RedirectController {

    @Autowired
    lateinit var service: KeyMapperService

    @RequestMapping("/")
    fun home() = "home"

    @RequestMapping("/{key}")
    fun redirect(@PathVariable("key") key : String, response: HttpServletResponse){
        val result = service.getLink(key)
        when (result) {
            is KeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, result.link);
                response.status = 302
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = 404
            }
        }
    }

    companion object {
        private val HEADER_NAME = "Location"
    }

}