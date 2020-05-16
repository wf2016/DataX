package com.fri.sjcs.csdm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fri.sjcs.csdm.controller.base.BaseController;
import com.fri.sjcs.csdm.model.dto.RwxxDto;
import com.fri.sjcs.csdm.model.entity.RwxxEntity;
import com.fri.sjcs.csdm.service.RwxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rwxx")
public class RwxxController extends BaseController {

    @Autowired
    RwxxService rwxxService;

    @RequestMapping("/add")
    public Object add(@RequestBody RwxxDto rwxxDto){
        RwxxEntity rwxxEntity = BeanUtil.toBean(rwxxDto,RwxxEntity.class);
        rwxxService.getBaseMapper().insert(rwxxEntity);
        rwxxService.getBaseMapper().selectCount(null);
        return "success";
    }
}
