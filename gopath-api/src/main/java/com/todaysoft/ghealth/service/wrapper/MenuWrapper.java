package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.mybaties.model.Menu;
import com.todaysoft.ghealth.DTO.MenuDTO;
import org.springframework.stereotype.Component;

@Component
public class MenuWrapper extends Wrapper<Menu, MenuDTO>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"submenus"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Menu source, MenuDTO target)
    {
        if (null != source.getSubmenus())
        {
            target.setSubmenus(wrap(source.getSubmenus()));
        }
    }
}
