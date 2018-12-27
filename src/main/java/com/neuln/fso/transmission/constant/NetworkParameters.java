package com.neuln.fso.transmission.constant;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * 网络参数常量类
 *
 * @author wzYun
 * @date 2018-12-25 15:04:50
 */
public class NetworkParameters {

    public static final Integer NUM_OF_FSO_NODE = 50;
    public static final Integer NUM_OF_TRANSCEIVER_PER_NODE = 8;

    /**
     * 每个FSO节点的最大发送功率限制(单位：mw)：平均每个TR的发送功率为10mw
     */
    public static final Double MAX_TOTAL_TX_POWER = 80.0;
    public static final Integer NUM_OF_TRAFFIC_FLOWS = 5;
    /**
     * 单位：packets/ms
     */

    public static final Integer PACKET_ARRIVAL_RATE = 2000;
    /**
     * 单位：ms
     */
    public static final Integer SIMULATION_DURATION = 1000;

    /**
     * 单位：km
     */
    public static final Integer NETWORK_X_SPAN = 10;

    /**
     * 单位：km
     */
    public static final Integer NETWORK_Y_SPAN = 7;

    /**
     * 定义网络左上角的点为BASE POINT
     */
    public static final Integer NETWORK_BASE_POINT_X = 0;
    public static final Integer NETWORK_BASE_POINT_Y = 0;

    /**
     * 每时隙长度：5个时间单位（例如：ms）
     */
    public static final Double TIME_SLOT_LENGTH = 5.0d;
    /**
     * 光波长1550nm
     */
    public static final Double WAVELENGTH = (1550e-9);
    /**
     * 环境参数：假设晴朗天气，此时环境参数为1dB/km，即10^0.1
     */
    public static final Double ENVIRONMENT_COEFFICIENT = (pow(10, 0.1));
    /**
     * 高斯白噪声双边功率谱密度：-174dBm/Hz，即10^(-17.4)mw/Hz
     */
    public static final Double NOISE_POWER_DENSITY = (pow(10, -17.4));
    /**
     * 信道带宽10GHz
     */
    public static final Double CHANNEL_BANDWIDTH = (10 * pow(10, 9));
    /**
     * 大气湍流（atmospheric turbulence）引起的损耗下限
     */
    public static final Double TURBULENCE_LOWER = 0.01d;
    /**
     * 大气湍流（atmospheric turbulence）引起的损耗上限
     */
    public static final Double TURBULENCE_UPPER = 0.2d;
    /**
     * 噪声功率
     */
    public static final Double NOISE_POWER = (NOISE_POWER_DENSITY * CHANNEL_BANDWIDTH);

    public static final Integer NUM_OF_PACKET_PER_FLOW = PACKET_ARRIVAL_RATE * SIMULATION_DURATION;
    /**
     * 数据包长度1024Byte
     */
    public static final Integer PACKET_LENGTH = (1024 * 8);

    public static final Integer NUM_OF_TIME_SLOTS = (int) (SIMULATION_DURATION / TIME_SLOT_LENGTH);
    /**
     * 按照平均功率为源节点的FSO发送器设置发送功率
     */
    public static final Double SOURCE_TRANSMIT_POWER = (MAX_TOTAL_TX_POWER / NUM_OF_TRANSCEIVER_PER_NODE);
    /**
     * 单位：km
     */
    public static final Double DISTANCE_THRESHOLD = 2.0;
    /**
     * 单位：Gbps
     */
    public static final Double BANDWIDTH_OF_TRAFFIC = 20.0;

    //double  SNR_THRESHOLD=1.0;//单位：dB
    /**
     * 无量纲，信噪比阈值
     */
    public static final Double SNR_THRESHOLD = 1.2589;
    /**
     * 信道容量阈值
     */
    public static final Double CAPACITY_THRESHOLD = CHANNEL_BANDWIDTH * (log(1 + SNR_THRESHOLD) / log(2));


    public static final Integer END_TO_END_DISTANCE = 4;


    /**
     * 收发口面积的影响
     */
    public static final Double APERTURE_AREA_TX = 3.14 * 0.01 * 0.01;
    public static final Double APERTURE_AREA_RX = 3.14 * 0.01 * 0.01;


}
