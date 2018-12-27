package com.neuln.fso.transmission.service.impl;


import com.neuln.fso.transmission.dao.*;
import com.neuln.fso.transmission.entity.*;
import com.neuln.fso.transmission.service.InitService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.neuln.fso.transmission.constant.NetworkParameters.*;


/**
 * InitService的实现
 *
 * @author wzYun
 * @date 2018-12-25 12:21:13
 */
@Service
public class InitServiceImpl implements InitService {

    @Resource
    private FsoNodeDao fsoNodeDao;
    @Resource
    private TransceiverDao transceiverDao;
    @Resource
    private ChannelModelDao channelModelDao;
    @Resource
    private TrafficDao trafficDao;
    @Resource
    private TimeSlotDao timeSlotDao;

    @Value("classpath:s_d.txt")
    private org.springframework.core.io.Resource sToDResource;

    @Override
    public void initFsoNodes() {
        fsoNodeDao.dropFsoNode();
        fsoNodeDao.createFsoNode();
        List<FsoNode> fsoNodes = new ArrayList<>();
        //初始化fso节点位置信息
        double xPosition = 1;
        double yPosition = 0;
        for (int i = 0; i < NUM_OF_FSO_NODE; i++) {
            if (yPosition >= NETWORK_Y_SPAN) {
                yPosition = 1;
                xPosition++;
            } else {
                yPosition++;
            }
            fsoNodes.add(new FsoNode(xPosition, yPosition));
        }
        fsoNodeDao.addFsoNodes(fsoNodes);
    }

    @Override
    public void initTransceivers() {
        transceiverDao.dropTransceiver();
        transceiverDao.createTransceiver();
        List<Transceiver> transceiverList = new ArrayList<>();
        List<FsoNode> fsoNodeList = fsoNodeDao.getFsoNodeList();
        for (FsoNode fsoNode : fsoNodeList) {
            for (int i = 0; i < NUM_OF_TRANSCEIVER_PER_NODE; i++) {
                transceiverList.add(new Transceiver(fsoNode.getId()));
            }
        }
        transceiverDao.addTransceivers(transceiverList);
    }

    @Override
    public void initChannelModel() {
        channelModelDao.dropChannelModel();
        channelModelDao.createChannelModel();
        List<ChannelModel> channelModelList = new ArrayList<>();
        List<FsoNode> fsoNodeList = fsoNodeDao.getFsoNodeList();
        for (FsoNode fsoNodeSour : fsoNodeList) {
            for (FsoNode fsoNodeDest : fsoNodeList) {
                if (!fsoNodeSour.equals(fsoNodeDest)) {
                    ChannelModel channelModel = new ChannelModel();
                    channelModel.setSrcNodeId(fsoNodeSour.getId());
                    channelModel.setDestNodeId(fsoNodeDest.getId());
                    //设置节点间距离
                    double xDistance = Math.abs(fsoNodeDest.getXPosition() - fsoNodeSour.getXPosition());
                    double yDistance = Math.abs(fsoNodeDest.getYPosition() - fsoNodeSour.getYPosition());
                    double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
                    channelModel.setDistance(distance);
                    //设置大气损耗？？？
                    int atmoTurbLossTemp = (int) (TURBULENCE_UPPER * (1.0e+5) - TURBULENCE_LOWER * (1.0e+5) + 1 + TURBULENCE_LOWER * (1.0e+5));
                    double atmoTurbLoss = new Random().nextInt(atmoTurbLossTemp) / (1.0e+5);
                    channelModel.setAtmoTurbLoss(atmoTurbLoss);
                    double propagationLoss = APERTURE_AREA_TX * APERTURE_AREA_RX * Math.exp(-1 * ENVIRONMENT_COEFFICIENT * distance) / ((WAVELENGTH * distance * 1000) * (WAVELENGTH * distance * 1000));
                    channelModel.setPropagationLoss(propagationLoss);
                    double channelGain = atmoTurbLoss * propagationLoss;
                    channelModel.setChannelGain(channelGain);
                    double transmitPower = MAX_TOTAL_TX_POWER / NUM_OF_TRANSCEIVER_PER_NODE;
                    double snr = (channelGain * channelGain * transmitPower) / NOISE_POWER;
                    channelModel.setSignalNoiseRatio(snr);
                    double capacity = CHANNEL_BANDWIDTH * (Math.log(1 + snr) / Math.log(2));
                    channelModel.setCapacity(capacity);
                    channelModelList.add(channelModel);
                }

            }
        }
        channelModelDao.addChannelModels(channelModelList);

    }

    @Override
    public void initTraffic() throws Exception {
        trafficDao.dropTraffic();
        trafficDao.createTraffic();

        List<Traffic> traffics = new ArrayList<>(NUM_OF_TRAFFIC_FLOWS);

        if (!sToDResource.exists()) {
            throw new RuntimeException("无法获取s_d资源文件");
        }
        String sToD = IOUtils.toString(sToDResource.getInputStream(), Charset.forName("UTF-8"));
        String[] split = sToD.split("\\r\\n");
        if (split.length != NUM_OF_TRAFFIC_FLOWS) {
            throw new RuntimeException("资源文件源节点目的节点对数量输入不对");
        }
        for (int i = 0; i < NUM_OF_TRAFFIC_FLOWS; i++) {
            Traffic traffic = new Traffic();
            traffic.setId(i);
            try {

                String[] split1 = split[i].split(":");
                traffic.setSourNodeId(Integer.parseInt(split1[0]));
                traffic.setDestNodeId(Integer.parseInt(split1[1]));
            } catch (NumberFormatException e) {
                throw new RuntimeException("输入格式有误");
            }
            traffics.add(traffic);
        }
        trafficDao.addTraffics(traffics);
        initTimeSlots(traffics);
    }

    private void initTimeSlots(List<Traffic> trafficList) {
        timeSlotDao.dropTimeSlot();
        timeSlotDao.createTimeSlot();
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (Traffic traffic : trafficList) {
            for (int i = 0; i < NUM_OF_TIME_SLOTS; i++) {
                TimeSlot timeSlot = new TimeSlot();
                Integer packetsCountInSLot = traffic.getPacketsInSlotCount()[i];
                timeSlot.setTimeSlotId(i);
                timeSlot.setTrafficId(traffic.getId());
                timeSlot.setPacketsInSlotCount(packetsCountInSLot);
                timeSlots.add(timeSlot);
            }
        }
        timeSlotDao.addTimeSlots(timeSlots);

    }
}
