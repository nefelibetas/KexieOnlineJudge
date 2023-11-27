package com.fish.entity.vo;

import com.fish.entity.pojo.Account;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class AccountVO implements Serializable {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public String getGithubAddress() {
        return githubAddress;
    }

    public void setGithubAddress(String githubAddress) {
        this.githubAddress = githubAddress;
    }
}
