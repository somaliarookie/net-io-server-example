package com.weile.mvc.controller;

import jdk.internal.jline.internal.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Auth weile
 * @Time 2019/11/30 17:46
 * @Description demo controller
 **/

@Slf4j
@RestController
public class TestController {

    @RequestMapping("/test")
    public String getSth() {

        return "sth";

    }

    /**
     * process with custom response time {rt}
     *
     * @param rt
     * @return
     */
    @RequestMapping(value = "/mvc/rt/{rt}")
    public String getWithLantency(@PathVariable Long rt) {

        Log.info();

        try {
            TimeUnit.MILLISECONDS.sleep(rt);   // 1
        } catch (InterruptedException e) {
            return "Error during thread sleep";
        }

        return "mvc response";
    }

}
