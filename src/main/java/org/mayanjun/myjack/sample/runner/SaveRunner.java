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

import org.mayanjun.myjack.sample.AbstractTestRunner;
import org.mayanjun.myjack.sample.Runner;
import org.mayanjun.myjack.sample.RunnerConfig;
import org.mayanjun.myjack.sample.bean.Contacts;
import org.mayanjun.myjack.sample.bean.Gender;
import org.mayanjun.myjack.sample.bean.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Runner(RunnerConfig.SAVE)
public class SaveRunner extends AbstractTestRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SaveRunner.class);

    @Override
    public void run() {
        LOG.info("Saving 20 students...");
        int count = 20;
        for (int i = 0; i < count; i++) {
            Student student = new Student();
            student.setBirthdate(randomDate());
            student.setGender(randomGender());
            student.setName("Student" + i);
            student.setScore(randomScore());

            Contacts contacts = new Contacts();
            contacts.setEmail("student" + i + "@xxx.com");
            contacts.setMobile(randomMobile());
            student.setContacts(contacts);
            dao.save(student);
        }
        LOG.info("{} students is save success", count);
    }

    public static final char NUMBERS [] = "0123456789".toCharArray();

    private String randomMobile() {
        StringBuffer sb = new StringBuffer("1");
        for (int i = 0; i < 10; i++) {
            sb.append(NUMBERS[new Double(Math.random() * NUMBERS.length).intValue()]);
        }
        return sb.toString();
    }

    private Double randomScore() {
        return 20 + Math.random() * 80;
    }

    private Gender randomGender() {
        return Gender.values()[new Double(Math.random() * Gender.values().length).intValue()];
    }

    private Date randomDate() {
        long tenYears = TimeUnit.DAYS.toMillis(365 * 30);
        long randMills = (long) Math.floor(Math.random() * tenYears);
        return new Date(randMills);
    }

    @Override
    public String description() {
        return "Test save students";
    }
}
