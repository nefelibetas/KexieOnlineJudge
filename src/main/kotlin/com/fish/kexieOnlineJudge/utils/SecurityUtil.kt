package com.fish.kexieOnlineJudge.utils

import com.fish.kexieOnlineJudge.exception.ServiceException
import com.fish.kexieOnlineJudge.exception.ServiceExceptionEnum
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
}
