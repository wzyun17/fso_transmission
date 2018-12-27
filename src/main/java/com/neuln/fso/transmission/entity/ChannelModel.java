package com.neuln.fso.transmission.entity;

import lombok.Data;

/**
 * 两节点间的信道
 *
 * @author wzYun
 * @date 2018-12-25 17:00:04
 */
@Data
public class ChannelModel {

    private Integer id;

    private Integer srcNodeId;

    private Integer destNodeId;

    private Double distance;

    private Double atmoTurbLoss;

    private Double propagationLoss;

    private Double channelGain;

    private Double signalNoiseRatio;

    private Double capacity;


}
