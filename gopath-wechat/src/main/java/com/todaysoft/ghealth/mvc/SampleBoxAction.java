package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.service.ISampleBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xjw
 * @Date: 2018/10/17 15:54
 */
@Controller
@RequestMapping("/sampleBox")
public class SampleBoxAction
{
    @Autowired
    private ISampleBoxService sampleBoxService;
    
    @RequestMapping("/bind.jsp")
    public String bind()
    {
        return "sampleBox/sampleBox_bind";
    }
    
    @PostMapping("/binding.jsp")
    public String bind(OrderDTO data)
    {
        sampleBoxService.bind(data);
        return "redirect:/order/list.jsp";
    }
    
    @GetMapping("/detail.jsp")
    public String deatils(String id, ModelMap model)
    {
        model.addAttribute("data", sampleBoxService.detail(id));
        return "sampleBox/sampleBox_detail";
    }

    @ResponseBody
    @GetMapping("/validateSampleCode.jsp")
    public SampleBoxDTO getOrderDTOBySampleBoxCode(String code)
    {
        return sampleBoxService.getOrderDTOBySampleBoxCode(code);
    }
}
