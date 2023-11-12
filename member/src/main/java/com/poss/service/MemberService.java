package com.poss.service;

import com.poss.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/***
 * @title MemberService
 * @description
 * @author poss
 * @version 1.0.0
 * @create 2023/11/12 18:51
 **/
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
