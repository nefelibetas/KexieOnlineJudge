package com.fish.mapper

import com.fish.entity.dto.RegisterAccountDTO
import com.fish.entity.dto.UpdateAccountDTO
import com.fish.entity.pojo.Account
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface AccountMapper : BaseMapper<Account> {
    @Select("select * from oj_account where enabled = true and email = #{email}")
    fun getAccountByEmail(@Param("email") email: String): Account
    @Update("update oj_account set role_id = #{roleId} where user_id = #{userId}")
    fun changeAccountRole(@Param("userId") userId: String, @Param("roleId") roleId: Long): Int
    @Select("select * from oj_account where enabled =true and user_id = #{userId}")
    fun getAccount(@Param("userId") userId: String): Account
    fun addAccount(registerAccountDTO: RegisterAccountDTO): Int
    fun updateAccount(updateAccountDTO: UpdateAccountDTO): Int
}
