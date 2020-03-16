package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Job;
import com.example.entity.Jobneed;
import com.example.entity.Workuser;
import com.example.service.IApplyService;
import com.example.service.IJobService;
import com.example.service.IJobneedService;
import com.example.service.IWorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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

    @RequestMapping("/postIndex")
    public String postIndex(Model model){
        List<Job> jobs = iJobService.queryJobList();
        model.addAttribute("jobs",jobs);
        return "postList";
    }

    /**
     * 按照时间查询招聘信息
     * @return
     */
    @RequestMapping("/postByTime/{day}")
    public String postByTime(@PathVariable("day") String day, Model model,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        String minpay = (String) session.getAttribute("minpay");
        String maxpay = (String) session.getAttribute("maxpay");
        String natureid = (String) session.getAttribute("natureid");
        String minyears = (String) session.getAttribute("minyears");
        String maxyears = (String) session.getAttribute("maxyears");
        String education = (String) session.getAttribute("education");
        String minscale = (String) session.getAttribute("minscale");
        String maxscale = (String) session.getAttribute("maxscale");
        int daY = Integer.parseInt(day);
        float minPay = 0;
        float maxPay = 0;
        int natureId = 0;
        int minYears = -1;
        int maxYears = 0;
        int minScale = 0;
        int maxScale = 0;
        if(minpay != null)
            minPay = Float.parseFloat(minpay);
        if(maxpay != null)
            maxPay = Float.parseFloat(maxpay);
        if(natureid != null)
            natureId = Integer.parseInt(natureid);
        if(minyears !=null)
            minYears = Integer.parseInt(minyears);
        if(maxyears != null){
            maxYears = Integer.parseInt(maxyears);
        }
        if(minscale !=null)
            minScale = Integer.parseInt(minscale);
        if(maxscale !=null)
            maxScale = Integer.parseInt(maxscale);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, daY, minPay, maxPay,natureId,minYears,maxYears,education,minScale,maxScale);
        model.addAttribute("jobs",jobs);
        session.setAttribute("day",day);
        return "postList";
    }

    /**
     * 按照发布时间查询招聘信息
     * @param jname 职位名
     * @param address 公司地址
     * @return
     */
    @RequestMapping("/search")
    public String query(String jname, String address, Model model, HttpSession session){
        session.setAttribute("address",address);
        session.setAttribute("jname",jname);
        List<Job> jobs = iJobService.queryJobListByQuery(jname, address);
        session.removeAttribute("minpay");
        session.removeAttribute("maxpay");
        session.removeAttribute("natureid");
        session.removeAttribute("minyears");
        session.removeAttribute("maxyears");
        session.removeAttribute("education");
        session.removeAttribute("minscale");
        session.removeAttribute("maxscale");
        session.removeAttribute("day");
        model.addAttribute("jobs",jobs);
        return "postList";
    }

    /**
     * 按照薪资区间查询招聘信息
     * @param min 薪资最小值
     * @param max 薪资最大值
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/postByPay/{min}/{max}")
    public String postByPay(@PathVariable("min")String min,@PathVariable("max")String max,Model model,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        String natureid = (String) session.getAttribute("natureid");
        String minyears = (String) session.getAttribute("minyears");
        String maxyears = (String) session.getAttribute("maxyears");
        String education = (String) session.getAttribute("education");
        String minscale = (String) session.getAttribute("minscale");
        String maxscale = (String) session.getAttribute("minscale");
        String day = (String) session.getAttribute("day");
        float minPay = 0;
        float maxPay = 0;
        int natureId = 0;
        int minYears = -1;
        int maxYears = 0;
        int minScale = 0;
        int maxScale = 0;
        int daY = 0;
        if(min != null)
            minPay = Float.parseFloat(min);
        if(max != null)
            maxPay = Float.parseFloat(max);
        if(natureid != null)
            natureId = Integer.parseInt(natureid);
        if(minyears !=null)
            minYears = Integer.parseInt(minyears);
        if(maxyears != null)
            maxYears = Integer.parseInt(maxyears);
        if(minscale !=null)
            minScale = Integer.parseInt(minscale);
        if(maxscale !=null)
            maxScale = Integer.parseInt(maxscale);
        if(day != null)
            daY = Integer.parseInt(day);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, daY, minPay, maxPay,natureId,minYears,maxYears,education,minScale,maxScale);
        session.setAttribute("minpay",min);
        session.setAttribute("maxpay",max);
        model.addAttribute("jobs",jobs);
        return "postList";
    }
    @RequestMapping("/postByNature/{natureid}")
    public String postByNature(@PathVariable("natureid") String natureid,Model model,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        String minyears = (String) session.getAttribute("minyears");
        String maxyears = (String) session.getAttribute("maxyears");
        String education = (String) session.getAttribute("education");
        String minscale = (String) session.getAttribute("minscale");
        String maxscale = (String) session.getAttribute("minscale");
        String minpay = (String) session.getAttribute("minpay");
        String maxpay = (String) session.getAttribute("maxpay");
        String day = (String) session.getAttribute("day");
        float minPay = 0;
        float maxPay = 0;
        int natureId = 0;
        int minYears = -1;
        int maxYears = 0;
        int minScale = 0;
        int maxScale = 0;
        int daY = 0;
        if(minpay != null)
            minPay = Float.parseFloat(minpay);
        if(maxpay != null)
            maxPay = Float.parseFloat(maxpay);
        if(natureid != null)
            natureId = Integer.parseInt(natureid);
        if(minyears !=null)
            minYears = Integer.parseInt(minyears);
        if(maxyears != null)
            maxYears = Integer.parseInt(maxyears);
        if(minscale !=null)
            minScale = Integer.parseInt(minscale);
        if(maxscale !=null)
            maxScale = Integer.parseInt(maxscale);
        if(day != null)
            daY = Integer.parseInt(day);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, daY, minPay, maxPay,natureId,minYears,maxYears,education,minScale,maxScale);
        session.setAttribute("natureid",natureid);
        model.addAttribute("jobs",jobs);
        return "postList";
    }

    @RequestMapping("/postByYears/{minyears}/{maxyears}")
    public String postByYears(@PathVariable("minyears") String minyears,@PathVariable("maxyears")String maxyears,Model model,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        String education = (String) session.getAttribute("education");
        String minscale = (String) session.getAttribute("minscale");
        String maxscale = (String) session.getAttribute("minscale");
        String minpay = (String) session.getAttribute("minpay");
        String maxpay = (String) session.getAttribute("maxpay");
        String day = (String) session.getAttribute("day");
        String natureid = (String) session.getAttribute("natureid");
        float minPay = 0;
        float maxPay = 0;
        int natureId = 0;
        int minYears = -1;
        int maxYears = 0;
        int minScale = 0;
        int maxScale = 0;
        int daY = 0;
        if(minpay != null)
            minPay = Float.parseFloat(minpay);
        if(maxpay != null)
            maxPay = Float.parseFloat(maxpay);
        if(natureid != null)
            natureId = Integer.parseInt(natureid);
        if(minyears !=null)
            minYears = Integer.parseInt(minyears);
        if(maxyears != null)
            maxYears = Integer.parseInt(maxyears);
        if(minscale !=null)
            minScale = Integer.parseInt(minscale);
        if(maxscale !=null)
            maxScale = Integer.parseInt(maxscale);
        if(day != null)
            daY = Integer.parseInt(day);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, daY, minPay, maxPay,natureId,minYears,maxYears,education,minScale,maxScale);
        session.setAttribute("minyears",minyears);
        session.setAttribute("maxyears",maxyears);
        model.addAttribute("jobs",jobs);
        return "postList";
    }

    @RequestMapping(value = "/postByEdu/{edu}")
    public String postByEdu(@PathVariable(value = "edu",required = false)String edu,Model model,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        String minscale = (String) session.getAttribute("minscale");
        String maxscale = (String) session.getAttribute("minscale");
        String minpay = (String) session.getAttribute("minpay");
        String maxpay = (String) session.getAttribute("maxpay");
        String day = (String) session.getAttribute("day");
        String natureid = (String) session.getAttribute("natureid");
        String minyears = (String) session.getAttribute("minyears");
        String maxyears = (String) session.getAttribute("maxyears");
        float minPay = 0;
        float maxPay = 0;
        int natureId = 0;
        int minYears = -1;
        int maxYears = 0;
        int minScale = 0;
        int maxScale = 0;
        int daY = 0;
        if(minpay != null)
            minPay = Float.parseFloat(minpay);
        if(maxpay != null)
            maxPay = Float.parseFloat(maxpay);
        if(natureid != null)
            natureId = Integer.parseInt(natureid);
        if(minyears !=null)
            minYears = Integer.parseInt(minyears);
        if(maxyears != null)
            maxYears = Integer.parseInt(maxyears);
        if(minscale !=null)
            minScale = Integer.parseInt(minscale);
        if(maxscale !=null)
            maxScale = Integer.parseInt(maxscale);
        if(day != null)
            daY = Integer.parseInt(day);
        if(edu.equals("所有")){
            edu = null;
        }
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, daY, minPay, maxPay,natureId,minYears,maxYears,edu,minScale,maxScale);
        session.setAttribute("education",edu);
        model.addAttribute("jobs",jobs);
        return "postList";
    }

    @RequestMapping("/postByScale//{min}/{max}")
    public String postByScale(@PathVariable("min")String min,@PathVariable("max")String max,Model model,HttpSession session){
        String jname = (String) session.getAttribute("jname");
        String address = (String) session.getAttribute("address");
        String minpay = (String) session.getAttribute("minpay");
        String maxpay = (String) session.getAttribute("maxpay");
        String day = (String) session.getAttribute("day");
        String natureid = (String) session.getAttribute("natureid");
        String minyears = (String) session.getAttribute("minyears");
        String maxyears = (String) session.getAttribute("maxyears");
        String education = (String) session.getAttribute("education");
        float minPay = 0;
        float maxPay = 0;
        int natureId = 0;
        int minYears = -1;
        int maxYears = 0;
        int minScale = 0;
        int maxScale = 0;
        int daY = 0;
        if(minpay != null)
            minPay = Float.parseFloat(minpay);
        if(maxpay != null)
            maxPay = Float.parseFloat(maxpay);
        if(natureid != null)
            natureId = Integer.parseInt(natureid);
        if(minyears !=null)
            minYears = Integer.parseInt(minyears);
        if(maxyears != null)
            maxYears = Integer.parseInt(maxyears);
        if(min !=null)
            minScale = Integer.parseInt(min);
        if(max !=null)
            maxScale = Integer.parseInt(max);
        if(day != null)
            daY = Integer.parseInt(day);
        List<Job> jobs = iJobService.queryJobListScreen(jname, address, daY, minPay, maxPay,natureId,minYears,maxYears,education,minScale,maxScale);
        session.setAttribute("minscale",min);
        session.setAttribute("maxscale",max);
        model.addAttribute("jobs",jobs);
        return "postList";
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
