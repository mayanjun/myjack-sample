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

package org.mayanjun.myjack.sample.mapper;

import org.mayanjun.myjack.generator.DDL;
import org.mayanjun.myjack.sample.bean.Student;

public class SqlBuilder {

    public String dropTable(String tableName) {
        return String.format("drop table `%s`", tableName);
    }

    public String queryTable(String schema, String tableName) {
        return "select TABLE_NAME from `information_schema`.TABLES where TABLE_SCHEMA=#{schema} and TABLE_NAME=#{tableName}";
    }

    public String createStudentTable() throws Exception {
        return DDL.ddl(Student.class, null, false);
    }
}
