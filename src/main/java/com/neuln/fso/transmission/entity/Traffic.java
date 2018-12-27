package com.neuln.fso.transmission.entity;

import lombok.Data;

import java.util.List;
import java.util.Random;

import static com.neuln.fso.transmission.constant.NetworkParameters.*;

/**
 * 业务流实体类
 *
 * @author wzYun
 * @date 2018-12-26 17:45:36
 */
@Data
public class Traffic {

    private Integer id;

    private Integer sourNodeId;

    private Integer destNodeId;

    private Integer packetsCount;
    /**
     * 到达时间间隔
     */
    private Double[] arrivalInterval;
    /**
     * 到达时间
     */
    private Double[] arrivalTime;
    /**
     * 时间间隔内传的包数量
     */
    private Integer[] packetsInSlotCount;
    /**
     * 到达速率
     */
    private Integer arrivalRate = PACKET_ARRIVAL_RATE;
    /**
     * 带宽需求
     */
    private Double bandwidth;
    /**
     * 是否直接传
     */
    private Boolean oneHopFlag;
    /**
     * 分组个数
     */
    private Integer groupCount;

    private List<List<Link>> groups;

    public Traffic() {
        bandwidth = BANDWIDTH_OF_TRAFFIC;
        packetsCount = NUM_OF_PACKET_PER_FLOW;
        arrivalInterval = new Double[NUM_OF_PACKET_PER_FLOW];
        arrivalTime = new Double[NUM_OF_PACKET_PER_FLOW];
        packetsInSlotCount = new Integer[NUM_OF_TIME_SLOTS];
        for (int i = 0; i < NUM_OF_PACKET_PER_FLOW; i++) {
            arrivalInterval[i] = -1.0 / arrivalRate * Math.log(1.0 - new Random().nextInt(1000) / 1000.0);
            if (i == 0) {
                arrivalTime[i] = arrivalInterval[i];
            } else {
                arrivalTime[i] = arrivalTime[i - 1] + arrivalInterval[i];
            }
        }
        int j = 0;
        for (int i = 0; i < NUM_OF_TIME_SLOTS; i++) {
            packetsInSlotCount[i] = 0;
            for (; j < NUM_OF_PACKET_PER_FLOW; j++) {
                if ((arrivalTime[j] >= (i * TIME_SLOT_LENGTH )) && (arrivalTime[j] <= ((i + 1) * TIME_SLOT_LENGTH ))) {
                    packetsInSlotCount[i]++;
                } else {
                    break;
                }
            }
        }
    }
}
