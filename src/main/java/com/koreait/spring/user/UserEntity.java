package com.koreait.spring.user;

import lombok.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String unm;
    private int gender;
    private String regdt;
    private String profileImg;
}
