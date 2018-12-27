package com.neuln.fso.transmission.entity;

import lombok.Data;

import static com.neuln.fso.transmission.constant.NetworkParameters.NUM_OF_TRANSCEIVER_PER_NODE;

/**
 * 节点的实体类，主要包括一些基本属性
 *
 * @author wzYun
 * @date 2018-12-25 10:52:32
 */
@Data
public class FsoNode {

    private Integer id;

    private Double xPosition;

    private Double yPosition;

    private Integer transceiverCount = NUM_OF_TRANSCEIVER_PER_NODE;

    private Integer occupiedTRCount;

    private Integer[] trs = new Integer[transceiverCount];


    public FsoNode(Double xPosition, Double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        occupiedTRCount = 0;
    }

    public FsoNode() {

    }

}
