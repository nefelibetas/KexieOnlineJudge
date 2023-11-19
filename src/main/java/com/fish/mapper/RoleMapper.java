package com.fish.mapper;

import com.fish.entity.pojo.Role;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *  映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from oj_role where role_id = #{role_id}")
    Role getRole(@Param("role_id") Long roleId);
    @Select("select * from oj_role where role_name = #{roleName}")
    Role getRole(@Param("roleName")String roleName);
}
