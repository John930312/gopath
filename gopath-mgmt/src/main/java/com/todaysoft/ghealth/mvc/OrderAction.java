package com.todaysoft.ghealth.mvc;

import com.alibaba.fastjson.JSON;
import com.todaysoft.ghealth.DTO.AreaDTO;
import com.todaysoft.ghealth.exception.ServiceException;
import com.todaysoft.ghealth.model.Order;
import com.todaysoft.ghealth.model.ResponseResult;
import com.todaysoft.ghealth.model.SampleBox;
import com.todaysoft.ghealth.model.UploadRequest;
import com.todaysoft.ghealth.model.searcher.OrderSearcher;
import com.todaysoft.ghealth.service.IAreaService;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: xjw
 * @Date: 2018/10/16 14:44
 */
@Controller
@RequestMapping("/order")
public class OrderAction
{
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private UploadRequest uploadRequest;
    
    @RequestMapping("/list.jsp")
    public String paginations(OrderSearcher searcher, PagerArgs pagerArgs, ModelMap model, HttpSession session)
    {
        Pager<Order> pagination = orderService.pager(searcher, pagerArgs.getPageNo(), pagerArgs.getPageSize());
        model.addAttribute("pagination", pagination);
        model.addAttribute("searcher", searcher);
        session.setAttribute("s-searcher", searcher);
        return "order/order_list";
    }
    
    @GetMapping(value = "/modify.jsp")
    public String modify(String id, ModelMap model)
    {
        Order data = orderService.get(id);
        SampleBox sampleBox = data.getSampleBox();
        AreaDTO provinceArea = new AreaDTO();

        if (Objects.nonNull(sampleBox) &&!StringUtils.isEmpty(sampleBox.getProvince()))
        {
            provinceArea.setId(sampleBox.getProvince());
        }

        if (Objects.nonNull(sampleBox) &&!StringUtils.isEmpty(sampleBox.getProvince()))
        {
            List<AreaDTO> province = areaService.findByParentId(sampleBox.getProvince());
            model.addAttribute("province", province);
        }

        model.addAttribute("data", data);
        model.addAttribute("list", areaService.findProvince());
        return "order/order_form";
    }

    @GetMapping(value = "/modifyStatus.jsp")
    public String modifyStatus(Order data, ModelMap model, HttpSession session)
    {
       return modify(data, model, session);
    }

    @PostMapping(value = "/modify.jsp")
    public String modify(Order data, ModelMap model, HttpSession session)
    {
        orderService.modify(data);
        return redirectList(model, session, "/order/list.jsp");
    }
    
    @GetMapping("/display.jsp")
    public String display(String id, ModelMap model)
    {
        model.addAttribute("data", orderService.get(id));
        return "order/order_detail";
    }

    @RequestMapping("/getAreas")
    @ResponseBody
    public List<AreaDTO> findByParentId(ModelMap model, String parentId)
    {
        return areaService.findByParentId(parentId);
    }

    @ResponseBody
    @GetMapping("/validateSampleBoxCode.do")
    public Boolean isUniqueSampleBoxCode(String sampleBoxCode)
    {
        return orderService.isUniqueSampleBoxCode(sampleBoxCode);
    }

    @RequestMapping("/reload.do")
    public String reload(ModelMap model, HttpSession session)
    {
        return redirectList(model, session, "/order/list.jsp");
    }
    
    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }

    @ResponseBody
    @RequestMapping("/uploadOrder")
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

            targetFile = new File(rootPath+"order/", fileName);
            try
            {
                file.transferTo(targetFile);
                msg = "/order/getPDF?path="+rootPath+"order/" + fileName;
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
