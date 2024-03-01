package com.fish.keXieOpenJudge.utils

import com.fish.keXieOpenJudge.exception.ServiceException
import com.fish.keXieOpenJudge.exception.ServiceExceptionEnum
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtil {
    @JvmStatic
    fun getId(): String {
        val authentication = try {
            SecurityContextHolder.getContext().authentication
        } catch (_: Exception) {
            throw ServiceException(ServiceExceptionEnum.UN_LOGIN)
        }
        return authentication.credentials as String
    }
