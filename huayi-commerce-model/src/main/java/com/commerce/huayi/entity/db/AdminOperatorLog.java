package com.commerce.huayi.entity.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_admin_operator_log")
public class AdminOperatorLog implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 登录用户ID
     */
    @Column(name = "login_user")
    private String loginUser;

    /**
     * 登陆地址
     */
    @Column(name = "login_addr")
    private String loginAddr;

    /**
     * 操作接口
     */
    @Column(name = "interfaces")
    private String interfaces;

    /**
     * 入参
     */
    @Column(name = "`input`")
    private String input;

    /**
     * 出参
     */
    @Column(name = "`output`")
    private String output;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录用户ID
     *
     * @return login_user - 登录用户ID
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * 设置登录用户ID
     *
     * @param loginUser 登录用户ID
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * 获取登陆地址
     *
     * @return login_addr - 登陆地址
     */
    public String getLoginAddr() {
        return loginAddr;
    }

    /**
     * 设置登陆地址
     *
     * @param loginAddr 登陆地址
     */
    public void setLoginAddr(String loginAddr) {
        this.loginAddr = loginAddr;
    }

    /**
     * 获取操作接口
     *
     * @return interfaces - 操作接口
     */
    public String getInterfaces() {
        return interfaces;
    }

    /**
     * 设置操作接口
     *
     * @param interfaces 操作接口
     */
    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * 获取入参
     *
     * @return input - 入参
     */
    public String getInput() {
        return input;
    }

    /**
     * 设置入参
     *
     * @param input 入参
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * 获取出参
     *
     * @return output - 出参
     */
    public String getOutput() {
        return output;
    }

    /**
     * 设置出参
     *
     * @param output 出参
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginUser=").append(loginUser);
        sb.append(", loginAddr=").append(loginAddr);
        sb.append(", interfaces=").append(interfaces);
        sb.append(", input=").append(input);
        sb.append(", output=").append(output);
        sb.append(", createDate=").append(createDate);
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
        AdminOperatorLog other = (AdminOperatorLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginUser() == null ? other.getLoginUser() == null : this.getLoginUser().equals(other.getLoginUser()))
            && (this.getLoginAddr() == null ? other.getLoginAddr() == null : this.getLoginAddr().equals(other.getLoginAddr()))
            && (this.getInterfaces() == null ? other.getInterfaces() == null : this.getInterfaces().equals(other.getInterfaces()))
            && (this.getInput() == null ? other.getInput() == null : this.getInput().equals(other.getInput()))
            && (this.getOutput() == null ? other.getOutput() == null : this.getOutput().equals(other.getOutput()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginUser() == null) ? 0 : getLoginUser().hashCode());
        result = prime * result + ((getLoginAddr() == null) ? 0 : getLoginAddr().hashCode());
        result = prime * result + ((getInterfaces() == null) ? 0 : getInterfaces().hashCode());
        result = prime * result + ((getInput() == null) ? 0 : getInput().hashCode());
        result = prime * result + ((getOutput() == null) ? 0 : getOutput().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}