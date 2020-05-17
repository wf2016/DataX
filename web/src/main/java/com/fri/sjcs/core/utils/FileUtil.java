package com.fri.sjcs.core.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.fri.sjcs.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    //每次任务生成新的目录
    public static String getNpath(String path){
        Path path1 = Paths.get(path);
        if(Files.exists(path1)){
            getNpath(genPath());
        }
        return path;
    }

    //生成随机目录
    public static String genPath(){
        String path = StringUtils.join( new String[] {
                RwConstant.RW_DATA_PATH, DatePattern.PURE_DATE_FORMAT.format(new DateTime()), UuidUtil.get32UUID().substring(0,6)}, File.separator);
        return path;
    }
}
