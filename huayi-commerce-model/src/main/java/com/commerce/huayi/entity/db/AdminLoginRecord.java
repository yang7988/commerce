package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;

public class AdminLoginRecord implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 登陆用户ID
     */
    private String loginUser;

    /**
     * 登陆唯一标识
     */
    private String loginKey;

    /**
     * 登陆地址
     */
    private String loginAdd;

    /**
     * 登入时间
     */
    private Date loginInDate;

    /**
     * 登出时间
     */
    private Date loginOutDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登陆用户ID
     *
     * @return login_user - 登陆用户ID
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * 设置登陆用户ID
     *
     * @param loginUser 登陆用户ID
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * 获取登陆唯一标识
     *
     * @return login_key - 登陆唯一标识
     */
    public String getLoginKey() {
        return loginKey;
    }

    /**
     * 设置登陆唯一标识
     *
     * @param loginKey 登陆唯一标识
     */
    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    /**
     * 获取登陆地址
     *
     * @return login_add - 登陆地址
     */
    public String getLoginAdd() {
        return loginAdd;
    }

    /**
     * 设置登陆地址
     *
     * @param loginAdd 登陆地址
     */
    public void setLoginAdd(String loginAdd) {
        this.loginAdd = loginAdd;
    }

    /**
     * 获取登入时间
     *
     * @return login_in_date - 登入时间
     */
    public Date getLoginInDate() {
        return loginInDate;
    }

    /**
     * 设置登入时间
     *
     * @param loginInDate 登入时间
     */
    public void setLoginInDate(Date loginInDate) {
        this.loginInDate = loginInDate;
    }

    /**
     * 获取登出时间
     *
     * @return login_out_date - 登出时间
     */
    public Date getLoginOutDate() {
        return loginOutDate;
    }

    /**
     * 设置登出时间
     *
     * @param loginOutDate 登出时间
     */
    public void setLoginOutDate(Date loginOutDate) {
        this.loginOutDate = loginOutDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginUser=").append(loginUser);
        sb.append(", loginKey=").append(loginKey);
        sb.append(", loginAdd=").append(loginAdd);
        sb.append(", loginInDate=").append(loginInDate);
        sb.append(", loginOutDate=").append(loginOutDate);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AdminLoginRecord other = (AdminLoginRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginUser() == null ? other.getLoginUser() == null : this.getLoginUser().equals(other.getLoginUser()))
            && (this.getLoginKey() == null ? other.getLoginKey() == null : this.getLoginKey().equals(other.getLoginKey()))
            && (this.getLoginAdd() == null ? other.getLoginAdd() == null : this.getLoginAdd().equals(other.getLoginAdd()))
            && (this.getLoginInDate() == null ? other.getLoginInDate() == null : this.getLoginInDate().equals(other.getLoginInDate()))
            && (this.getLoginOutDate() == null ? other.getLoginOutDate() == null : this.getLoginOutDate().equals(other.getLoginOutDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginUser() == null) ? 0 : getLoginUser().hashCode());
        result = prime * result + ((getLoginKey() == null) ? 0 : getLoginKey().hashCode());
        result = prime * result + ((getLoginAdd() == null) ? 0 : getLoginAdd().hashCode());
        result = prime * result + ((getLoginInDate() == null) ? 0 : getLoginInDate().hashCode());
        result = prime * result + ((getLoginOutDate() == null) ? 0 : getLoginOutDate().hashCode());
        return result;
    }
}