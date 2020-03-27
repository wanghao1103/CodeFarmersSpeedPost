package com.example.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.entity.City;
import com.example.entity.Job;
import com.example.entity.Jobneed;
import com.example.entity.Workuser;
import com.example.service.*;
import com.example.util.Screen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Resource
    private IJobService iJobService;

    @Resource
    private IJobneedService iJobneedService;

    @Resource
    private IApplyService iApplyService;

    @Resource
    private IWorkerService iWorkerService;

    @Resource
    private ICityService iCityService;

    private Screen screen = new Screen();

    @RequestMapping("/postIndex")
    public String postIndex(Model model){
        List<City> cities = iCityService.list();
        List<Job> jobs = iJobService.queryJobList();
        model.addAttribute("cities",cities);
        model.addAttribute("jobs",jobs);
        return "postList";
    }

    /**
     * 按照时间查询招聘信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/postByTime/{day}")
    public String postByTime(@PathVariable("day") Integer day,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setDay(day);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, screen);
        System.out.println(JSONArray.toJSONString(jobs));
        return JSONArray.toJSONString(jobs);
    }

    /**
     * 按照发布时间查询招聘信息
     * @param jname 职位名
     * @param address 公司地址
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public String query(@RequestParam("jname")String jname,@RequestParam("address")String address, HttpSession session){
        session.setAttribute("address",address);
        session.setAttribute("jname",jname);
        List<Job> jobs = iJobService.queryJobListByQuery(jname, address);
        return JSONArray.toJSONString(jobs);
    }

    /**
     * 按照薪资区间查询招聘信息
     * @param min 薪资最小值
     * @param max 薪资最大值
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/postByPay/{min}/{max}")
    public String postByPay(@PathVariable("min")Float min,@PathVariable("max")Float max,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setMax(max);
        screen.setMin(min);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address,screen);
        return JSONArray.toJSONString(jobs);
    }
    @ResponseBody
    @RequestMapping("/postByNature/{natureid}")
    public String postByNature(@PathVariable("natureid") Integer natureid,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setNatureid(natureid);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, screen);
        return JSONArray.toJSONString(jobs);
    }
    @ResponseBody
    @RequestMapping("/postByYears/{minyears}/{maxyears}")
    public String postByYears(@PathVariable("minyears") Integer minyears,@PathVariable("maxyears")Integer maxyears,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setMinyears(minyears);
        screen.setMaxyears(maxyears);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, screen);
        return JSONArray.toJSONString(jobs);
    }
    @ResponseBody
    @RequestMapping(value = "/postByEdu/{edu}")
    public String postByEdu(@PathVariable(value = "edu",required = false)String edu,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        if(edu.equals("所有")){
            edu = null;
        }
        screen.setEducation(edu);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, screen);
        return JSONArray.toJSONString(jobs);
    }
    @ResponseBody
    @RequestMapping("/postByScale/{min}/{max}")
    public String postByScale(@PathVariable("min")Integer min,@PathVariable("max")Integer max,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setMinscale(min);
        screen.setMaxscale(max);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, screen);
        return JSONArray.toJSONString(jobs);
    }

    /**
     * 跳转jobneed页面
     * @return
     */
    @RequestMapping("/postInfoIndex/{jid}")
    public String postInfoIndex(@PathVariable("jid")Long jid, Model model,HttpSession session){
        Job job = iJobService.queryJobById(jid);
        Jobneed jobneed = iJobneedService.queryJobneedByJid(jid);
        Workuser user = (Workuser) session.getAttribute("wuser");
        Long wid = iWorkerService.queryWidByUserid(1L);
        int val= iApplyService.queryApply(wid,jid);
        System.out.println("val:"+val+"wid:"+wid+"jid"+jid);
        model.addAttribute("isApply",val);
        model.addAttribute("jobneed",jobneed);
        model.addAttribute("job",job);
        return "postInfo";
    }
}
