package org.mayanjun.myjack.sample.runner;

import org.mayanjun.myjack.sample.AbstractTestRunner;
import org.mayanjun.myjack.sample.Runner;
import org.mayanjun.myjack.sample.RunnerConfig;
import org.mayanjun.myjack.sample.mapper.CustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Runner(RunnerConfig.CREATE)
public class CreateRunner extends AbstractTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CreateRunner.class);

    @Override
    public String description() {
        return "Create tables";
    }

    @Override
    public void run() {
        CustomMapper mapper = dao.getDataBaseRouter().getDatabaseSession().getMapper(CustomMapper.class);
        int ret = mapper.createStudentTable();
        LOG.info("Table t_student create done, {} rows affected", ret);
    }
}
