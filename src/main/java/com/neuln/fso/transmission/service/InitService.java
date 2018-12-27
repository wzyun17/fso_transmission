package com.neuln.fso.transmission.service;

/**
 * 进行路由与时隙分配前的准备工作
 *
 * @author wzYun
 * @date 2018-12-25 12:03:33
 */
public interface InitService {

    /**
     * 初始化所有的FSO节点
     */
    void initFsoNodes();

    /**
     * 初始化FSO节点的收发器
     */
    void initTransceivers();


    /**
     * 初始化信道模型
     */
    void initChannelModel();

    /**
     * 初始化业务流
     *
     * @throws Exception;
     */
    void initTraffic() throws Exception;

}
