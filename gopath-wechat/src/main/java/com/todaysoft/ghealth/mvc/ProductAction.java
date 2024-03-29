package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.model.searcher.ProductSearcher;
import com.todaysoft.ghealth.service.IProductService;

import com.todaysoft.ghealth.service.ISlideshowService;
import com.todaysoft.ghealth.wechat.AccountContextHolder;
import com.todaysoft.ghealth.wechat.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductAction
{
    @Autowired
    private IProductService productService;

    @Autowired
    private AccountContextHolder holder;

    @Autowired
    private ISlideshowService slideshowService;


    @RequestMapping("/saoMa.jsp")
    public String saoMa(ProductSearcher searcher, ModelMap model, HttpSession session)
    {
        Account account = holder.getAccount();
        account.setLivePurchase(2);
        holder.setAccount(account);
        return "saoyisao";
    }

    /**
     * 首页产品列表
     * @param searcher
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/indexList.jsp")
    public String indexList(ProductSearcher searcher, ModelMap model, HttpSession session)
    {
        Account account = holder.getAccount();
        account.setAgencyId(searcher.getAgencyId());
        if (null == account.getLivePurchase()) {
            account.setAgencyId(searcher.getAgencyId());
            account.setLivePurchase(1);
            holder.setAccount(account);
        }

        List<ProductDTO> productList = productService.indexList(searcher);
        List<SlideshowDTO> slideshowList = slideshowService.indexList();
        model.addAttribute("products", productList);
        model.addAttribute("slideshows", slideshowList);
        model.addAttribute("openId", account.getOpenid());
        return "product/index_product_list";
    }

    /**
     * 问卷搜索产品列表
     * @param searcher
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/list.jsp")
    public String list(ProductSearcher searcher, ModelMap model, HttpSession session)
    {
        List<ProductDTO> productList = productService.list(searcher);
        model.addAttribute("products", productList);
        return "product/product_list";
    }

    @GetMapping("/detail.jsp")
    public String detail(String id, ModelMap model)
    {
        ProductDTO product = productService.get(id);
        model.addAttribute("data", product);
        model.addAttribute("openId", holder.getAccount().getOpenid());
        return "product/product_detail";
    }

}
