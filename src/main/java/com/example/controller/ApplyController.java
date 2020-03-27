package com.example.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.entity.Workuser;
import com.example.service.IApplyService;
import com.example.service.IWorkerService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private IApplyService iApplyService;

    @Resource
    private IWorkerService iWorkerService;

    @RequestMapping("/postApply/{jid}")
    public String apply(@PathVariable("jid") Long jid, HttpSession session){
        Workuser user = (Workuser) session.getAttribute("wuser");
        Long wid = iWorkerService.queryWidByUserid(1L);
        int rel = iApplyService.insertApply(wid,jid);
        Map map = new HashMap();
        if(rel>0){
            map.put("result","success");
            return JSONArray.toJSONString(map);
        }
        map.put("result","loser");
        return JSONArray.toJSONString(map);
    }
}
