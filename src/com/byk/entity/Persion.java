package com.byk.entity;
import java.io.Serializable;
import java.util.Date;
/**
 * @Author: ykbian
 * @Date: 2018/10/24 9:22
 * @Todo: 打印的员工信息实体类
 */
public class Persion implements Serializable {


    private static final long serialVersionUID = 1L;
    private String userName;        //姓名
    private String gender;          //性别
    private String nation;          // 民族
    private String address;        //住址
    private String IDNumber;        //身份证号码
    private int    age;            //启用日期
    private String qrCodePath;        //头像
    private Date   birthday;

    private String title;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Persion(String userName, String gender, String nation, String address, String IDNumber, int age, String qrCodePath, Date birthday, String title) {
        this.userName = userName;
        this.gender = gender;
        this.nation = nation;
        this.address = address;
        this.IDNumber = IDNumber;
        this.age = age;
        this.qrCodePath = qrCodePath;
        this.birthday = birthday;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Persion() {
    }
}