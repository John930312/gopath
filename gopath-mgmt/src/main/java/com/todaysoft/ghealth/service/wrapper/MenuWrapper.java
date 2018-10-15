package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.model.Menu;
import com.todaysoft.ghealth.DTO.MenuDTO;
import org.springframework.stereotype.Component;

@Component
public class MenuWrapper extends Wrapper<MenuDTO, Menu>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"submenus"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(MenuDTO source, Menu target)
    {
        if (null != source.getSubmenus())
        {
            target.setSubmenus(wrap(source.getSubmenus()));
        }
    }
}
