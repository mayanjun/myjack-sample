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

package org.mayanjun.myjack.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MainTest implements ApplicationRunner, ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(MainTest.class);

    public static final String SEP = "======================= {}. {} =======================";

    private ApplicationContext context;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, TestRunner> runnerMap = context.getBeansOfType(TestRunner.class);
        if (runnerMap != null && !runnerMap.isEmpty()) {
            runnerMap.values().stream().sorted(new OrderComparator()).forEach(e -> {
                if (e.ready()) {
                    executeRunner(e);
                } else {
                    LOG.info("!!!!!!!!!!! Runner '{}' was not ready", e.getClass().getSimpleName());
                }
            });
            LOG.info("ALL TEST-RUNNER IS PROCESSED");
        }
    }

    private void executeRunner(TestRunner runner) {
        LOG.info(SEP, runner.getOrder(), runner.getClass().getSimpleName());
        LOG.info("Description: {}", runner.description());

        long now = System.currentTimeMillis();
        try {
            runner.run();
            LOG.info("SUCCESS");
        } catch (Exception e) {
            LOG.info("ERROR", e);
        }
        LOG.info("Execute done: taking {} ms", System.currentTimeMillis() - now);
        LOG.info("");
        LOG.info("");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
