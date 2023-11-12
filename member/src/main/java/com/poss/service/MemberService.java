package com.poss.service;

import cn.hutool.core.collection.CollUtil;
import com.poss.domain.Member;
import com.poss.domain.MemberExample;
import com.poss.exception.BusinessException;
import com.poss.exception.BusinessExceptionEnum;
import com.poss.mapper.MemberMapper;
import com.poss.req.MemberRegisterReq;
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


    public long register(MemberRegisterReq memberRegisterReq) {

        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(memberRegisterReq.getMobile());
        List<Member> list = memberMapper.selectByExample(memberExample);
        
        if(CollUtil.isNotEmpty(list)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(memberRegisterReq.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }
}
