package com.dayuan.controller;

import com.dayuan.dto.ResponseDTO;
import com.dayuan.entity.User;

import com.dayuan.exception.BizException;
import com.dayuan.exception.ExceptionDispose;
import com.dayuan.service.UserService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping("/user")
public class LoginController extends ExceptionDispose {
    @Autowired
    UserService userService;

    //登录
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO login(String userName, String pwd,String captcha, HttpSession session, ModelMap modelMap){

        String captcha1 = (String) session.getAttribute("captcha");
        if (captcha1==null){
            throw new BizException("请刷新页面");
        }

        if (!captcha1.equalsIgnoreCase(captcha)){
            throw new BizException("验证码错误");
        }

        User user = userService.login(userName, pwd );
            session.setAttribute("userId",user.getId());
            return ResponseDTO.success();
    }



    //注册
    @RequestMapping(value = "/loginUp" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO loginUp(String userName, String pwd, ModelMap modelMap,String conPwd){
        userService.loginUp(userName,pwd,conPwd);
        modelMap.addAttribute("userName",userName);
        return ResponseDTO.success();
    }


    @RequestMapping("/out")
    public String out(HttpSession session){

        session.invalidate();
        return "redirect:/index.jsp";
    }


    //获取用户名
    @RequestMapping(value = "/getUserName" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO getUserName(HttpSession session){
        String userName = userService.getUserName(UserId.getUserId(session));
        return ResponseDTO.success(userName);
    }



    //上传头像
    @RequestMapping(value = "/upUserbuddha",method = RequestMethod.POST)
    public void upUserbuddha(
                            @RequestParam("avatar")MultipartFile userbuddha,
                             HttpServletResponse response,
                             HttpSession session){
        try(OutputStream os = response.getOutputStream();) {
            String filepath = "D:/atm/image/" +  UserId.getUserId(session) + ".jpg";
            userbuddha.transferTo(new File(filepath));
            os.write("<script>parent.showP('/user/showUserbuddha.do')</script>".getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("/showUserbuddha")
    @ResponseBody
    public void showUserbuddha( HttpSession session,HttpServletResponse response){
        String fileName = UserId.getUserId(session)+".jpg";
        try (FileInputStream fileinputStream = new FileInputStream("D:/atm/image/" + fileName);
             OutputStream outputStream= response.getOutputStream()) {
            int data = -1;
            byte[] buffer = new byte[612000];
            while(-1!=(data=fileinputStream.read(buffer))){
                outputStream.write(buffer);
                outputStream.flush();
            }

        }catch (Exception e){
                e.printStackTrace();
        }


    }




    @RequestMapping(value = "/upExcl",method = RequestMethod.POST)
    public void upExcl(
            @RequestParam("avatar")MultipartFile upExcl,
            HttpSession session){
            try {
            String filepath = "D:/atm/Excl/" +  "1111" + ".xsl";
                upExcl.transferTo(new File(filepath));

                Workbook wb = Workbook.getWorkbook(new File(filepath));
                Sheet[] sheets = wb.getSheets();

                for(Sheet sheet:sheets){
                    int columns = sheet.getRows();
                    for(int i=0;i<columns;i++){
                        Cell[] cells = sheet.getRow(i);
                        for(Cell cell:cells){
                            System.out.print(cell.getContents()+"\t");
                        }
                        System.out.println("");
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
                e.printStackTrace();
            }

    }

}
