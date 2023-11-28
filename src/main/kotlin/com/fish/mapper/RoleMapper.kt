package com.fish.mapper

import com.fish.entity.pojo.Role
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface RoleMapper : BaseMapper<Role?> {
    @Select("select * from oj_role where role_id = #{role_id}")
    fun getRoleById(@Param("role_id") roleId: Long): Role

    @Select("select * from oj_role where role_name = #{roleName}")
    fun getRoleByName(@Param("roleName") roleName: String): Role
}
