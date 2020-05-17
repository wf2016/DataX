package com.fri.sjcs.csdm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fri.sjcs.core.RwEngine;
import com.fri.sjcs.csdm.controller.base.BaseController;
import com.fri.sjcs.csdm.model.dto.RwxxDto;
import com.fri.sjcs.csdm.model.entity.RwxxEntity;
import com.fri.sjcs.csdm.service.RwxxService;
import com.fri.sjcs.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rwxx")
public class RwxxController extends BaseController {

    @Autowired
    RwxxService rwxxService;

    @RequestMapping("/addread")
    public Object add(@RequestBody RwxxDto rwxxDto){

        rwxxDto.setId(UuidUtil.get32UUID());
        RwxxEntity rwxxEntity = BeanUtil.toBean(rwxxDto,RwxxEntity.class);
        rwxxService.getBaseMapper().insert(rwxxEntity);
        RwEngine rwEngine = new RwEngine();
        rwEngine.entry("read",rwxxDto);
        return "success";
    }

    @RequestMapping("addwrite")
    public Object addwrite(@RequestBody RwxxDto rwxxDto){
        RwEngine rwEngine = new RwEngine();
        rwEngine.entry("write",rwxxDto);
        return "success";
    }
}
