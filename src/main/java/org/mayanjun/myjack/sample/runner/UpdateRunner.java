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

/**
 *
 */
@Runner(RunnerConfig.UPDATE)
public class UpdateRunner extends AbstractTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateRunner.class);

    @Override
    public String description() {
        return "Update fields...";
    }

    @Override
    public void run() {
        Query<Student> query = QueryBuilder.custom(Student.class)
                .andEquivalent("name", "Student0")
                .build();
        Student student = dao.queryOne(query);
        if (student != null) {
            LOG.info("Update student name Student0 to mayanjun");
            student.setName("mayanjun");
            int ret = dao.update(student);
            LOG.info("Student update done: id={}, ret={}", student.getId(), ret);
        }
    }
}
