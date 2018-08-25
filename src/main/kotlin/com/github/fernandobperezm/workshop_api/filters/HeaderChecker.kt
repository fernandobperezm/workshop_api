package com.github.fernandobperezm.workshop_api.filters

import com.github.fernandobperezm.workshop_api.headervalidator.ValidationHeader
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(1)
class HeaderChecker : Filter {
    override fun doFilter(
            request: ServletRequest?,
            response: ServletResponse?,
            chain: FilterChain?) {

        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse
        try {
            ValidationHeader().validate(httpRequest)
        } catch (exception: IllegalArgumentException ) {
            httpResponse.sendError(HttpStatus.BAD_REQUEST.value(), exception.message)
        }
        chain?.doFilter(httpRequest, httpResponse)

    }

    override fun init(filterConfig: FilterConfig?) {
    }

    override fun destroy() {
    }

}