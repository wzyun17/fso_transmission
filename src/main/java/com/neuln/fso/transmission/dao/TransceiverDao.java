package com.neuln.fso.transmission.dao;


import com.neuln.fso.transmission.entity.Transceiver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 节点的收发器的Dao
 *
 * @author wzYun
 * @date 2018-12-25 13:29:49
 */
@Mapper
public interface TransceiverDao {


    /**
     * 创建transceiver表
     */
    @Update("CREATE TABLE `transceiver` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收发器id',\n" +
            "  `nodeId` int(10) NOT NULL COMMENT '所属节点id',\n" +
            "  `queueLength` int(10) DEFAULT NULL COMMENT '队列长度',\n" +
            "  `transmitPower` float DEFAULT NULL COMMENT '收发器能量',\n" +
            "  `whichTimeSlot` int(11) DEFAULT NULL COMMENT '第几个时间窗口',\n" +
            "  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")
    void createTransceiver();


    /**
     * 删除表transceiver
     */
    @Update("drop table transceiver")
    void dropTransceiver();

    /**
     * 获取transceiver列表
     *
     * @param transceiverList transceiverList
     */
    @Insert({
            "<script>",
            "insert into transceiver(nodeId, queueLength, transmitPower, whichTimeSlot) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.nodeId}, #{item.queueLength}, #{item.transmitPower}, #{item.whichTimeSlot})",
            "</foreach>",
            "</script>"
    })
    void addTransceivers(List<Transceiver> transceiverList);


}

