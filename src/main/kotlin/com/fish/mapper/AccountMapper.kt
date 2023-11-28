package com.fish.mapper

import com.fish.entity.dto.RegisterAccountDTO
import com.fish.entity.pojo.Account
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Insert
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

    @Update("update oj_account set enabled = false where user_id = #{userId}")
    fun deleteAccount(@Param("userId") userId: String): Int

    @Update("update oj_account set role_id = #{roleId} where user_id = #{userId}")
    fun changeAccountRole(@Param("userId") userId: String, @Param("roleId") roleId: Long): Int

    @Select(
        "select user_id, avatar, username, student_id, gender, specialty, nickname, email, qq, github_address, blog_address " +
                "from oj_account " +
                "where enabled = true and role_id < 3"
    )
    fun getAdmins(): ArrayList<Account>

    @Select(
        "select user_id, avatar, username, student_id, gender, specialty, nickname, email, qq, github_address, blog_address " +
                "from oj_account " +
                "where enabled = true and role_id = 3"
    )
    fun getCustomAccounts(): ArrayList<Account>

    @Select("select * from oj_account where enabled =true and user_id = #{userId}")
    fun getAccount(@Param("userId") userId: String): Account

    @Insert(
        "insert into " +
                "oj_account (nickname, avatar, student_id, username, password, gender, email, specialty, qq, blog_address, github_address) " +
                "value (#{nickname}, #{avatar}, #{studentId}, #{username}, #{password}, #{gender}, #{email}, #{specialty}, #{qq}, #{blog_address}, #{github_address})"
    )
    fun addAccount(registerAccountDTO: RegisterAccountDTO): Int
}
