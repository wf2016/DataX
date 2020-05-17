package com.fri.sjcs.core;

import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.core.util.ConfigParser;
import com.alibaba.fastjson.JSON;
import com.fri.sjcs.core.tasks.RwTaskContainer;
import com.fri.sjcs.core.utils.RwConstant;
import com.fri.sjcs.csdm.model.dto.RwxxDto;

public class RwEngine {

    public void start(Configuration rwjob){
        RwTaskContainer rwTaskContainer = new RwTaskContainer(rwjob);
        rwTaskContainer.start();
    }

    public void entry(String jobtype, RwxxDto rwxxDto){

        Configuration rwjob = ConfigParser.parseCoreConfig(RwConstant.RW_CONF_PATH);

        rwjob.set("jobtype",jobtype);
        rwjob.set("rwxx", JSON.toJSON(rwxxDto));

        if(jobtype.equals("read")){
            rwjob.set("rdjob.job.content[0].reader",JSON.toJSON(rwxxDto.getRdjson()));
        }else {
            rwjob.set("wrjob.jsonjob.job.content[0].writer", rwxxDto.getWrjson());
            rwjob.set("wrjob.filenames",JSON.toJSON(rwxxDto.getFilenames()));
        }
        this.start(rwjob);
    }
}
