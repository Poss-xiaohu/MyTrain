package com.poss.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.poss.domain.Member;
import com.poss.domain.MemberExample;
import com.poss.exception.BusinessException;
import com.poss.exception.BusinessExceptionEnum;
import com.poss.mapper.MemberMapper;
import com.poss.req.MemberRegisterReq;
import com.poss.req.MemberSendCodeReq;
import com.poss.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

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
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(memberRegisterReq.getMobile());
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }

        // 生成验证码
        // String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);

        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("保存短信记录表");

        // 对接短信通道，发送短信
        LOG.info("对接短信通道");
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
