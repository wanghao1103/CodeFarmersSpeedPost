package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.Workuser;
import com.example.service.impl.WorkuserServiceImpl;
import com.example.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zhenzi.sms.ZhenziSmsClient;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanghao
 * @since 2020-03-04
 */
@Controller
@RequestMapping("/workuser")
public class WorkuserController {
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    private String appId = "104774";
    private String appSecret = "ca1e812f-5be3-4604-8c8a-7b186cd65962";

        @Autowired
        private WorkuserServiceImpl workuserServiceimpl;
        @RequestMapping("/user")
        public String user(){
            return "loginbar ";
        }

    @RequestMapping("/Login")
    public String Login() {
        return "Login";
    }

    @RequestMapping("/index")
    public String list() {

        return "index";
    }

    @GetMapping("/add")
    @ResponseBody
    public Object insert(String wuser, String wpassword, String wphone, String wemail, HttpSession HttpSession) {
        boolean flag = false;
        Map<String, Object> map = new HashMap<String,Object>();
        int user = workuserServiceimpl.add(wuser, wpassword, wemail, wphone);
        if (user > 0) {

            flag = true;
            map.put("flag", flag);
            map.put("msg", "注册成功");
        } else {
            map.put("flag", flag);
            map.put("msg", "注册失败");
        }
        return JSONArray.toJSONString(map);
    }
        @GetMapping("/u")
        public String login(@RequestParam("wuser") String wuser,@RequestParam("wpassword") String wpassword,String verify_code,HttpSession session){
                Workuser workuser = workuserServiceimpl.Login(wuser,wpassword);
                    System.out.println(verify_code);
                if(workuser!=null && verify_code!=null){
                        session.setAttribute("wuser",wuser);
                        return "postList";
                }
            return "redirect:user";
        }
        @RequestMapping("/fiu")
        public String fincode(@RequestParam("wphone") String wphone,HttpSession session){
                    session.setAttribute("wuser",wphone);
                   return "postList";
        }
        @RequestMapping("/shou")
        public String en(){
            return "ficode";
        }
        @RequestMapping("/code")
        public void getCode(HttpServletResponse res, HttpServletRequest req) throws Exception{
                //1.生成随机的验证码及图片
                Object[] objs = VerifyCodeUtils.createImage();
                //2.将验证码存入session
                String imgcode = (String) objs[0];
                HttpSession session = req.getSession();
                session.setAttribute("imgcode", imgcode);
                //3.将图片输出给浏览器
                BufferedImage img = (BufferedImage) objs[1];
                res.setContentType("image/png");
                //服务器自动创建输出流，目标指向浏览器
                OutputStream os = res.getOutputStream();
                ImageIO.write(img, "png", os);
                os.close();
        }
    @ResponseBody
    @RequestMapping("/fitness/code")
    public boolean getCode(@RequestParam("wphone") String wphone, HttpSession httpSession){
        try {
            JSONObject json = null;
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            String result = client.send(wphone, "您的验证码为:" + code + "，该码有效期为5分钟，该码只能使用一次!");

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                return  false;
            }
            //将验证码存到session中,同时存入创建时间
            json = new JSONObject();
            json.put("wphone",wphone);
            json.put("code",code);
            json.put("createTime",System.currentTimeMillis());
            // 将认证码存入SESSION
            httpSession.setAttribute("code",json);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @RequestMapping("/ficode")
    public String fins(){
            return "ficode";
    }
}
