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

import org.mayanjun.myjack.core.query.Query;
import org.mayanjun.myjack.core.query.QueryBuilder;
import org.mayanjun.myjack.sample.AbstractTestRunner;
import org.mayanjun.myjack.sample.Runner;
import org.mayanjun.myjack.sample.RunnerConfig;
import org.mayanjun.myjack.sample.bean.Gender;
import org.mayanjun.myjack.sample.bean.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Runner(RunnerConfig.QUERY)
public class QueryRunner extends AbstractTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(QueryRunner.class);

    @Override
    public void run() {
        LOG.info("Query all boy students... ");
        Query<Student> query = QueryBuilder.custom(Student.class)
                .andEquivalent("gender", Gender.MALE)
                .andGroup()
                .andLike("contacts.mobile", "13%")
                .orLike("contacts.mobile", "15%")
                .endGroup()
                .build();
        List<Student> list = dao.query(query);
        list.forEach(e -> LOG.info("Query: {}", e));
    }

    @Override
    public String description() {
        return "Test query students";
    }
}
