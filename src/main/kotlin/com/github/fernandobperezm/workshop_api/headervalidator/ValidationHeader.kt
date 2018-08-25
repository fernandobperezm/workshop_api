package com.github.fernandobperezm.workshop_api.headervalidator

import javax.servlet.http.HttpServletRequest

class ValidationHeader : IValidationHeaderRule {
    override fun validate(requestToValidate: HttpServletRequest): Boolean {
        return requestToValidate.headerNames.toList().fold(true) { previousValid, actualHeaderName ->
            if (!previousValid)  false
            else when (actualHeaderName) {
                "content-type" -> ValidationContentType().validate(requestToValidate.getHeader(actualHeaderName))
                "cache-control" -> ValidationCacheControl().validate(requestToValidate.getHeader(actualHeaderName))
                else -> true
            }
        }
    }
}

class ValidationContentType : IValidationHeaderPartRule {
    override fun validate(headerToValidate: String): Boolean {
        val contentTypeRegex = Regex("(application/json; charset=utf-8)|(charset=utf-8; application/json)")
        if (!contentTypeRegex.matches(headerToValidate))
            throw IllegalArgumentException("Content-type is not valid")
        return true
    }
}

class ValidationCacheControl : IValidationHeaderPartRule {
    override fun validate(headerToValidate: String): Boolean {
        val cacheControlRegex = Regex("(no-cache)|(private)|(max-age=3600)|(no-transform)")
        if (!cacheControlRegex.matches(headerToValidate))
            throw IllegalArgumentException("Cache-Control is not valid")
        return true
    }
}