package com.fri.sjcs.csdm.dao.interfaces;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fri.sjcs.csdm.model.entity.RwxxEntity;

public interface RwxxDao extends BaseMapper<RwxxEntity> {

    //创建数据库
    int createtb(String rwxx);
}
