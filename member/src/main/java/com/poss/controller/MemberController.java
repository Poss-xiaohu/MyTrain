package com.poss.controller;

import com.poss.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @title MemberController
 * @description
 * @author poss
 * @version 1.0.0
 * @create 2023/11/12 19:02
 **/
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public /*CommonResp<Integer>*/int count() {
        int count = memberService.count();
//        CommonResp<Integer> commonResp = new CommonResp<>();
//        commonResp.setContent(count);
//        return commonResp;
        return count;
    }
}
