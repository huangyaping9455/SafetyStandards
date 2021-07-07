package org.springblade.platform.qiyeshouye.controller;

import org.springblade.platform.qiyeshouye.springTemplate.basicJsonBean.JsonBean;
import org.springblade.platform.qiyeshouye.springTemplate.basicJsonBean.JsonProducer;
import org.springblade.platform.qiyeshouye.springTemplate.basicString.StringProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 司马缸砸缸了
 * @date 2019/12/27 16:09
 * @description
 */

@RestController
@RequestMapping("/platform/test")
public class TestController {

    @Autowired
    private StringProducer producer;

    @Autowired
    private JsonProducer jsonProducer;

    @GetMapping("/string")
    public String string() {
        for (int i = 0; i < 5; i++) {
            producer.send("Message【" + i + "】：my name is simagangzagangl");
        }

        return "success";
    }

    @GetMapping("/json")
    public String json() {
        JsonBean json = new JsonBean();
        json.setMessageId("1");
        json.setMessageContent("this is message");
        jsonProducer.send(json);

        return "success";
    }
}
