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

import org.mayanjun.myjack.api.query.Query;
import org.mayanjun.myjack.api.query.QueryBuilder;
import org.mayanjun.myjack.sample.AbstractTestRunner;
import org.mayanjun.myjack.sample.Runner;
import org.mayanjun.myjack.sample.RunnerConfig;
import org.mayanjun.myjack.sample.bean.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Runner(RunnerConfig.DELETE)
public class DeleteRunner extends AbstractTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteRunner.class);

    @Override
    public String description() {
        return "Update fields...";
    }

    @Override
    public void run() {
        int age = 30;
        LOG.info("Delete all student who were older than {} years", age);
        Date date = new Date(System.currentTimeMillis() - (TimeUnit.DAYS.toMillis(age * 365)));

        Query<Student> query = QueryBuilder.custom(Student.class)
                .andLessThan("birthdate", date)
                .build();
        int delete = dao.delete(query);
        LOG.info("{} student was deleted", delete);
    }
}
