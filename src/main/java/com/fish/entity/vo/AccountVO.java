package com.fish.entity.vo;

import com.fish.entity.pojo.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {
    private String userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String gender;
    /**
     * 专业
     */
    private String specialty;
    /**
     * 博客地址
     */
    private String blogAddress;
    /**
     * GitHub地址
     */
    private String githubAddress;
    public AccountVO(Account account) {
        this.userId = account.getUserId();
        this.nickname = account.getNickname();
        this.avatar = account.getAvatar();
        this.gender = account.getGender();
        this.specialty = account.getSpecialty();
        this.blogAddress = account.getBlogAddress();
        this.githubAddress = account.getGithubAddress();
    }
}
