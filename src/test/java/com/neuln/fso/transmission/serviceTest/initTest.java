package com.neuln.fso.transmission.serviceTest;

import com.neuln.fso.transmission.FsoTransmissionApplicationTests;
import com.neuln.fso.transmission.service.InitService;
import org.junit.Test;

import javax.annotation.Resource;

public class initTest extends FsoTransmissionApplicationTests {

    @Resource
    private InitService initService;

    @Test
    public void initFsoNodeTest() {
        initService.initFsoNodes();


        System.out.println("111");
    }

    @Test
    public void initTransceiverTest() {
        initService.initTransceivers();

        System.out.println("111");
    }


    @Test
    public void initChannelModel() {
        initService.initChannelModel();

        System.out.println("111");
    }

    @Test
    public void initTraffic() {
        try {
            initService.initTraffic();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("!11");

    }

}
