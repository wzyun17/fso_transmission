package com.neuln.fso.transmission.entity;


import lombok.Data;

import static com.neuln.fso.transmission.constant.NetworkParameters.MAX_TOTAL_TX_POWER;
import static com.neuln.fso.transmission.constant.NetworkParameters.NUM_OF_TRANSCEIVER_PER_NODE;

/**
 * 节点收发器的实体类
 *
 * @author wzYun
 * @date 2018-12-25 13:32:38
 */
@Data
public class Transceiver {

    private Integer id;

    private Integer queueLength;

    private Double transmitPower;

    private Integer whichTimeSlot;

    private Integer nodeId;


    public Transceiver(int nodeId) {
        this.queueLength = 0;
        this.transmitPower = MAX_TOTAL_TX_POWER / NUM_OF_TRANSCEIVER_PER_NODE;
        this.whichTimeSlot = 0;
        this.nodeId = nodeId;
    }

    public Transceiver() {

    }
}
