package com.todaysoft.ghealth.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/")
public class IndexAction {

    @RequestMapping("/MP_verify_SfVPe5ZrWfrQs6fX.txt")
    public void electronicPolicyDownload(HttpServletRequest request, HttpServletResponse response) {
        String path = Class.class.getClass().getResource("/").getPath()+"templates/static/MP_verify_SfVPe5ZrWfrQs6fX.txt";
        File file = new File(path);
        if (file.exists()) {
            //打开后,强制下载
            //response.setContentType("application/force-download");// 设置强制下载不打开
            //response.addHeader("Content-Disposition","attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
