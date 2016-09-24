package com.quanquan.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Quanquan
 * Date: 2016/9/24.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private static Log log = LogFactory.getLog(UploadController.class);

    @RequestMapping(value={"/",""}, method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String upload(HttpServletRequest req) throws Exception{
        log.info("=============POST===uploadFile===========");
        FileOutputStream fos = null ;
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
        String tomcatPath = System.getProperty("user.dir").replace("bin", "file");
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
            File uploadFilePath = new File(tomcatPath + "Upload" + File.separator + sdfDate.format(new Date())
                    + File.separator );
            log.info("uploadFilePath:  " + uploadFilePath);
            log.info("fileName:  " + fileName);
            if ( !uploadFilePath.exists()){
                boolean flag = uploadFilePath.mkdirs() ;//uploadFilePath 本身创建
                log.info("============Create NewFilePath:[" + flag +"][" + uploadFilePath + "]=======");
            }
            fos = new FileOutputStream( uploadFilePath + File.separator + sdfTime.format(new Date())
                    + "-" + fileName) ;

            fos.write(file.getBytes());
            fos.flush();
            fos.close();

            return fileName + " upload success";
        }catch (Exception e){
            e.printStackTrace();
            return fileName + " upload success";
        }
    }
    @RequestMapping(value={"/",""},method=RequestMethod.GET)
    public String uploadGet(HttpServletRequest req) throws Exception{
        System.out.println("=============GET");
        return "hello";
    }
}
