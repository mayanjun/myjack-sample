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

package org.mayanjun.myjack.sample.bean;

import org.mayanjun.myjack.core.annotation.Column;
import org.mayanjun.myjack.core.enums.DataType;

public class Contacts {

    @Column(comment = "mobile phone", type = DataType.VARCHAR, length = "20")
    private String mobile;

    @Column(comment = "email", type = DataType.VARCHAR, length = "64")
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
