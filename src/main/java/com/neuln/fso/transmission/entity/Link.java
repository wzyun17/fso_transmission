package com.neuln.fso.transmission.entity;

import lombok.Data;

/**
 * fso传输的链路的实体类
 *
 * @author wzYun
 * @date 2018-12-26 18:00:12
 */
@Data
public class Link {

    private Integer sourNodeId;

    private Integer destNodeId;

    private Integer sourTr;

    private Integer destTr;

    private Double transmitPower;

    private Double channelGain;

    private Double capacity;

    private Double[] throughout;

    private Double[] timeSlot;

    private String[] timeSlotName;

    private Integer occupiedTrafficId;

    private Integer groupId;

    private Integer linkCategory;

    public Link() {

    }

}
