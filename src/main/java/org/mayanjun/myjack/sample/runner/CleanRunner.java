/*
 * Copyright 2016-2018 mayanjun.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mayanjun.myjack.sample.runner;

import org.apache.commons.lang3.StringUtils;
import org.mayanjun.myjack.api.annotation.Table;
import org.mayanjun.myjack.sample.AbstractTestRunner;
import org.mayanjun.myjack.sample.Runner;
import org.mayanjun.myjack.sample.RunnerConfig;
import org.mayanjun.myjack.sample.bean.Student;
import org.mayanjun.myjack.sample.mapper.CustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Runner(RunnerConfig.CLEAN)
public class CleanRunner extends AbstractTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CleanRunner.class);

    @Override
    public String description() {
        return "Drop table and delete all data in database";
    }

    @Override
    public void run() {
        LOG.info("Drop all tables...");

        Table table = Student.class.getAnnotation(Table.class);
        String tableName = table.value();

        CustomMapper mapper = dao.getDataBaseRouter().getDatabaseSession().getMapper(CustomMapper.class);
        String tn = mapper.queryTable("myjack", tableName);

        if (StringUtils.isNotBlank(tn)) { // table is existent
            LOG.info("Table {} is existent", tableName);
            LOG.info("Dropping table {} ...", tableName);
            int ret = mapper.dropTable(tableName);
            LOG.info("Drop table done, table={}, {} rows affected", tableName, ret);
        } else {
            LOG.info("Table {} is not existent", tableName);
        }
    }
}
