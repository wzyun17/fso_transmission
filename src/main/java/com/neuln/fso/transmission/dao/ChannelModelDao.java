package com.neuln.fso.transmission.dao;


import com.neuln.fso.transmission.entity.ChannelModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 两节点间信道模型的Dao
 *
 * @author wzYun
 * @date 2018-12-25 17:48:25
 */
@Mapper
public interface ChannelModelDao {

    /**
     * 创建channel_model表
     */
    @Update("CREATE TABLE `channel_model` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
            "  `srcNodeId` int(11) DEFAULT NULL COMMENT '源节点id',\n" +
            "  `destNodeId` int(11) DEFAULT NULL COMMENT '目的节点id',\n" +
            "  `distance` float(25,0) DEFAULT NULL COMMENT '距离',\n" +
            "  `atmoTurbLoss` float(25,0) DEFAULT NULL,\n" +
            "  `propagationLoss` float(25,0) DEFAULT NULL,\n" +
            "  `channelGain` float(25,0) DEFAULT NULL COMMENT '信道增益',\n" +
            "  `signalNoiseRatio` float(25,0) DEFAULT NULL COMMENT '信噪比',\n" +
            "  `capacity` float(25,0) DEFAULT NULL COMMENT '信道容量',\n" +
            "  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=2451 DEFAULT CHARSET=utf8;")
    void createChannelModel();


    /**
     * 删除channel_model表
     */
    @Update("drop table channel_model")
    void dropChannelModel();


    /**
     * 批量添加 channelModel
     *
     * @param channelModels channelModels
     */
    @Insert({
            "<script>",
            "insert into channel_model(srcNodeId, destNodeId, distance, atmoTurbLoss,propagationLoss,channelGain,signalNoiseRatio,capacity) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.srcNodeId}, #{item.destNodeId}, #{item.distance}, #{item.atmoTurbLoss}, #{item.propagationLoss}, #{item.channelGain}, " +
                    "#{item.signalNoiseRatio}, #{item.capacity})",
            "</foreach>",
            "</script>"
    })
    void addChannelModels(List<ChannelModel> channelModels);
}
