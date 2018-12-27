package com.neuln.fso.transmission.entity;

import lombok.Data;

/**
 * 时间窗口实体类
 *
 * @author wzYun
 * @date 2018-12-27 09:40:27
 */
@Data
public class TimeSlot {

    private Integer id;

    private Integer timeSlotId;

    private Integer trafficId;

    private Integer packetsInSlotCount;


}
