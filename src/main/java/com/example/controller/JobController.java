package com.example.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Screen screen = null;

    @RequestMapping("/goRegies")
    public String goReg(){
        return "redirect:/workuser/Login";
    }

    @RequestMapping("/goLogin")
    public String goLogin(){
        return "redirect:/workuser/user";
    }

    @RequestMapping("outLogin")
    public String outLogin(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/job/postIndex";
    }

    @RequestMapping("/postIndex")
    public String postIndex(Model model){
        screen = new Screen();
        List<City> cities = iCityService.list();
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobList(page);
        model.addAttribute("cities",cities);
        model.addAttribute("jobs",iPage.getRecords());
        model.addAttribute("iPage",iPage);
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
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListScreen(page,jname, address, screen);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
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
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListByQuery(page,jname, address);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
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
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListScreen(page,jname, address,screen);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/postByNature/{natureid}")
    public String postByNature(@PathVariable("natureid") Integer natureid,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setNatureid(natureid);
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListScreen(page,jname, address,screen);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/postByYears/{minyears}/{maxyears}")
    public String postByYears(@PathVariable("minyears") Integer minyears,@PathVariable("maxyears")Integer maxyears,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setMinyears(minyears);
        screen.setMaxyears(maxyears);
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListScreen(page,jname, address,screen);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
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
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListScreen(page,jname, address,screen);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
    }
    @ResponseBody
    @RequestMapping("/postByScale/{min}/{max}")
    public String postByScale(@PathVariable("min")Integer min,@PathVariable("max")Integer max,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        screen.setMinscale(min);
        screen.setMaxscale(max);
        Page<Job> page = new Page<>(1,3);
        IPage<Job> iPage = iJobService.queryJobListScreen(page,jname, address,screen);
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping("/page/{pagenum}")
    public String page(@PathVariable("pagenum")int pagenum,HttpSession session){
        Page<Job> page = new Page<>(pagenum,3);
        IPage<Job> iPage=null;
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        if((jname == "" || jname ==null) && (address == "" || address ==null)){
            iPage = iJobService.queryJobListScreen(page,jname,address,screen);
        }else{
            iPage = iJobService.queryJobListScreen(page,jname,address,screen);
        }
        Map map = new HashMap<>();
        map.put("jobs",iPage.getRecords());
        map.put("page",iPage.getPages());
        return JSONArray.toJSONString(map);
    }

    /**
     * 跳转jobneed页面
     * @return
     */
    @RequestMapping("/postInfoIndex/{jid}")
    public String postInfoIndex(@PathVariable("jid")Long jid, Model model,HttpSession session){
        Job job = iJobService.queryJobById(jid);
        Jobneed jobneed = iJobneedService.queryJobneedByJid(jid);
        Workuser user = (Workuser) session.getAttribute("user");
        if(user == null){
            return "redirect:/workuser/user";
        }
        Long wid = iWorkerService.queryWidByUserid(user.getUserid());
        int val= iApplyService.queryApply(wid,jid);
        System.out.println("val:"+val+"wid:"+wid+"jid"+jid);
        model.addAttribute("isApply",val);
        model.addAttribute("jobneed",jobneed);
        model.addAttribute("job",job);
        return "postInfo";

    }
}
