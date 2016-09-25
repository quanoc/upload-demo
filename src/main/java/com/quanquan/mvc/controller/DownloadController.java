package com.quanquan.mvc.controller;

import com.quanquan.util.FileOperateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Quanquan
 * Date: 2016/9/24.
 */
@Controller
@RequestMapping("/download")
public class DownloadController {
    private static Log log = LogFactory.getLog(DownloadController.class);

    /**
     * DO: fileList
     * @param request
     * @return
     */
    @RequestMapping(value={"", "/"})
    public ModelAndView list(HttpServletRequest request){
        init();  //init FILEDIR
        log.info("init FILEDIR:" + FileOperateUtil.FILEDIR);
        request.setAttribute("map", getMap());  //get fileList map

        /**
         * print Map
         */
        Map<String,String> map = getMap();
        for (Map.Entry entry: map.entrySet()) {
            log.info(entry.getKey() + "==" + entry.getValue());
        }
        return new ModelAndView("filelist");
    }

    /**
     * DO: downloadFile
     * @param request
     * @param response
     */
    @RequestMapping(value="file")
    public void download(HttpServletRequest request, HttpServletResponse response){
        init();
        try {
            String downloadfFileName = request.getParameter("filename");
            downloadfFileName = new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8");
            String fileName = downloadfFileName.substring(downloadfFileName.indexOf("_")+1);
            String userAgent = request.getHeader("User-Agent");
            byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
            fileName = new String(bytes, "ISO-8859-1");
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
            FileOperateUtil.download(downloadfFileName, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * DO: init FILEDIR
     */
    private void init() {
        if(FileOperateUtil.FILEDIR == null){
            FileOperateUtil.FILEDIR = System.getProperty("user.dir").replace("bin", "file") + "Upload" ;
        }
    }
    private Map<String, String> getMap(){
        Map<String, String> map = new HashMap<String, String>();
        File[] files = new File(FileOperateUtil.FILEDIR).listFiles();
        if(files != null){
            for (File file : files) {
                if(file.isDirectory()){
                    File[] files2 = file.listFiles();
                    if(files2 != null){
                        for (File file2 : files2) {
                            String name = file2.getName();
                            map.put(file2.getParentFile().getName() + "/" + name, name.substring(name.lastIndexOf("_")+1));
                        }
                    }
                }
            }
        }
        return map;
    }
}
