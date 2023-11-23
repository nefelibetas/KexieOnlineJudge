package com.fish.mapper;

import com.fish.entity.dto.RegisterAccountDTO;
import com.fish.entity.pojo.Account;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 *  映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
public interface AccountMapper extends BaseMapper<Account> {
    @Select("select * from oj_account where enabled = true and email = #{email}")
    Account getAccountByEmail(@Param("email") String email);
    @Update("update oj_account set enabled = false where user_id = #{userId}")
    int deleteAccount(@Param("userId")String userId);
    @Update("update oj_account set role_id = #{roleId} where user_id = #{userId}")
    int changeAccountRole(@Param("userId")String userId, @Param("roleId")Long roleId);
    @Select("select user_id, avatar, username, student_id, gender, specialty, nickname, email, qq, github_address, blog_address " +
            "from oj_account " +
            "where enabled = true and role_id < 3")
    ArrayList<Account> getAdmins();
    @Select("select user_id, avatar, username, student_id, gender, specialty, nickname, email, qq, github_address, blog_address " +
            "from oj_account " +
            "where enabled = true and role_id = 3")
    ArrayList<Account> getCustomAccounts();
    @Select("select * from oj_account where enabled =true and user_id = #{userId}")
    Account getAccount(@Param("userId")String userId);
    @Insert("insert into " +
            "oj_account (nickname, avatar, student_id, username, password, gender, email, specialty, qq, blog_address, github_address) " +
            "value (#{nickname}, #{avatar}, #{studentId}, #{username}, #{password}, #{gender}, #{email}, #{specialty}, #{qq}, #{blog_address}, #{github_address})")
    int addAccount(RegisterAccountDTO registerAccountDTO);
}
