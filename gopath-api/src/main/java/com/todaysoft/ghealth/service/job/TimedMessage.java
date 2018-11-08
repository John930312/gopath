package com.todaysoft.ghealth.service.job;

import com.todaysoft.ghealth.mybatis.mapper.ProductMapper;
import com.todaysoft.ghealth.mybatis.model.Product;
import com.todaysoft.ghealth.mybatis.model.query.ProductQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Component
@EnableScheduling
public class TimedMessage
{
    private static Logger logger = LoggerFactory.getLogger(TimedMessage.class);

    @Autowired
    private ProductMapper productMapper;

    /**
     * 定时任务，每天00:00
     * 产品优惠折扣状态修改
     */
    @Scheduled(cron = "0 0 0 ? * *")
    public void sendInMorning()
    {
        //获取优惠产品
        LocalDateTime now = LocalDateTime.now();
        Date endDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        ProductQuery query = new ProductQuery();
        query.setDiscount("1");
        List<Product> products = productMapper.query(query);

        System.out.println("--------------开始比较---------------");
        if (!CollectionUtils.isEmpty(products)){
            products.forEach(p->{
                Optional.ofNullable(p.getEndTime()).ifPresent(x->{
                    if (endDate.after(x))
                    {
                        p.setDiscount(false);
                        productMapper.modify(p);
                        logger.info(p.getName()+"优惠已过期");
                    }
                });
            });
        }
    }

}
