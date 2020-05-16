package com.fri.sjcs.csdm.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rwxx")
public class RwxxEntity {

    //任务主键
    String id;

    //中心节点生成的任务id
    String rwid;

    //是否创建表 0:若表不存在则不执行且返回错误，1:若表不存在，则创建表， 2:若表存在，则删除表后重建表， 3:正常执行，若检查字段不通过则返回失败
    String iscreate;

    //任务类型 (只包括一次执行 或者 定时执行)（一次全量，一次某条件之后的全量， 定时增量，定时条件增量）
    String rwlx;

    //定时执行
    String corn;

    //读取数据库的条件
    String cwhere;

    //用户自定义的条件
    String userwhere;

    //中心节点配置的源json
    String rdjson;

    //中心节点配置的目标json; 可能同时同步给多个不同的数据库
    // ！注意 该json对象为数组
    String wrjson;

    //任务状态 未启动 运行中 已完成
    String rwzt;
}
