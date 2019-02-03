package org.mayanjun.myjack.sample.bean;

import org.mayanjun.myjack.core.annotation.*;
import org.mayanjun.myjack.core.entity.DeletableEntity;
import org.mayanjun.myjack.core.enums.DataType;

import java.text.SimpleDateFormat;
import java.util.Date;

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

@Table(comment = "student",
        value = "t_student",
        indexes = {
        @Index(value = "idx_name", columns = @IndexColumn("name"))
        }
)
public class Student extends DeletableEntity {

    @Column(comment = "name", type = DataType.VARCHAR, length = "32")
    private String name;

    @Column(type = DataType.DATE)
    private Date birthdate;

    @Column(comment = "Score", type = DataType.DOUBLE, length = "5,2")
    private Double score;

    @Column(comment = "Gender", type = DataType.VARCHAR, length = "32")
    private Gender gender;

    // as a component
    @Component
    private Contacts contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Student() {
    }

    public Student(Long id) {
        super(id);
    }

    public Student(String name, Date birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birthdate=" + formatBirthdate() +
                ", score=" + score +
                ", gender=" + gender +
                ", contacts=" + contacts +
                '}';
    }

    private String formatBirthdate() {
        if (birthdate == null) return "-";
        return new SimpleDateFormat("yyyy-MM-dd").format(birthdate);
    }
}
