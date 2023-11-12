package com.poss.service;

import cn.hutool.core.collection.CollUtil;
import com.poss.domain.Member;
import com.poss.domain.MemberExample;
import com.poss.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public long register(String mobile) {

        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        
        if(CollUtil.isNotEmpty(list)){
            throw new RuntimeException("手机号已经注册！");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
