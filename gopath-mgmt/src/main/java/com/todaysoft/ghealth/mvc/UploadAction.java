package com.todaysoft.ghealth.mvc;

import com.alibaba.fastjson.JSON;
import com.todaysoft.ghealth.model.ResponseResult;
import com.todaysoft.ghealth.model.UploadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: zyf
 * @Date: 2018/11/21 14:13
 */
@Controller
@RequestMapping("/upload")
public class UploadAction {
    @Autowired
    private UploadRequest uploadRequest;

    @ResponseBody
    @RequestMapping("/uploadImg")
    public String uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request)
    {

        File targetFile = null;
        String msg = "";//返回存储路径
        int code = 1;
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "")
        {
            String rootPath = uploadRequest.getFilePath();

            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileF;//新的文件名

            targetFile = new File(rootPath + "slideshow/", fileName);
            try
            {
                file.transferTo(targetFile);
                msg = rootPath + "slideshow/" + fileName;
                code = 0;
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(JSON.toJSONString(ResponseResult.result(code, msg)));
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }

    @RequestMapping(value = "/getImg", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getImage(@RequestParam(value = "path", required = false) String path) throws IOException
    {
        if (path != null)
        {
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
        else
        {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/uploadPdf")
    public String uploadPdf(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request)
    {

        File targetFile = null;
        String msg = "";//返回存储路径
        int code = 1;
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "")
        {
            String rootPath = uploadRequest.getFilePath();

            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileF;//新的文件名

            targetFile = new File(rootPath+"order/", fileName);
            try
            {
                file.transferTo(targetFile);
                msg = "/upload/getPDF?path="+rootPath+"order/" + fileName;
                code = 0;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }

    @RequestMapping(value = "/getPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public byte[] getPDF(@RequestParam(value = "path", required = false) String path) throws IOException
    {
        if (path != null)
        {
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
        else
        {
            return null;
        }
    }
}
