package com.fri.sjcs.core.tasks;

import com.alibaba.datax.common.util.Configuration;
import com.fri.sjcs.core.RwAbstractContainer;
import com.fri.sjcs.core.tasks.runner.RwReaderRunner;
import com.fri.sjcs.core.tasks.runner.RwWriterRunner;

public class RwTaskContainer extends RwAbstractContainer {

    String id;

    String rwid;

    public RwTaskContainer(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void start() {
        String jobtype = this.configuration.getString("jobtype");
        RwTaskExecutor rwTaskExecutor = new RwTaskExecutor(this.getConfiguration());
        if(jobtype.equals("read")){
            rwTaskExecutor.readerThread.start();
        }else {
            rwTaskExecutor.writerThread.start();
        }
    }

    class RwTaskExecutor {
        private Configuration rwTaskConfig;

        private Thread readerThread;

        private Thread writerThread;

        private RwReaderRunner rwReaderRunner;

        private RwWriterRunner rwWriterRunner;

        public RwTaskExecutor(Configuration rwTaskConfig){
            this.rwTaskConfig = rwTaskConfig;

            //
            rwReaderRunner = new RwReaderRunner();
            rwReaderRunner.setJobConf(rwTaskConfig);
            this.readerThread = new Thread((Runnable) rwReaderRunner,"read");

            //
            rwWriterRunner = new RwWriterRunner();
            rwWriterRunner.setJobConf(rwTaskConfig);
            this.writerThread = new Thread((Runnable) rwWriterRunner,"write");

        }
    }

}
