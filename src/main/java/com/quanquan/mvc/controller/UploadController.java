package com.quanquan.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    Log log = LogFactory.getLog(UploadController.class);

    @RequestMapping(value={"/",""},method=RequestMethod.POST)
    public String upload(HttpServletRequest req) throws Exception{
        log.debug("=============POST===uploadFile===========");
        FileOutputStream fos = null ;
        try {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
            MultipartFile file = mreq.getFile("file");


            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
            String fileName = file.getOriginalFilename();
            String tomPath = System.getProperty("user.dir").replace("bin", "file");
            File uploadFilePath = new File(tomPath + "Upload" + File.separator + sdfDate.format(new Date())
                    + File.separator );
            log.debug("uploadFilePath:" + uploadFilePath);
            if ( !uploadFilePath.exists()){
                boolean flag = uploadFilePath.mkdirs() ;//uploadFilePath 本身创建
                System.out.println("============Create NewFilePath:[" + flag +"][" + uploadFilePath + "]=======");
            }

            fos = new FileOutputStream( uploadFilePath + File.separator + sdfTime.format(new Date())
                    + "-" + fileName) ;

            fos.write(file.getBytes());
            fos.flush();
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return "hello";
    }
    @RequestMapping(value={"/",""},method=RequestMethod.GET)
    public String uploadGet(HttpServletRequest req) throws Exception{

        System.out.println("=============GET");
        return "hello";
    }
}
