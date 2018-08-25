package com.github.fernandobperezm.workshop_api.headervalidator

import javax.servlet.http.HttpServletRequest

interface IValidationHeaderRule {
    fun validate(requestToValidate: HttpServletRequest): Boolean
}

interface IValidationHeaderPartRule {
    fun validate(headerToValidate: String): Boolean
}