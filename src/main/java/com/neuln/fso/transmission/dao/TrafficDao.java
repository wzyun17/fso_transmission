package com.neuln.fso.transmission.dao;

import com.neuln.fso.transmission.entity.Traffic;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 业务流的Dao
 *
 * @author wzYun
 * @date 2018-12-26 21:42:10
 */
@Mapper
public interface TrafficDao {


    /**
     * 创建traffic表
     */
    @Update("CREATE TABLE `traffic` (\n" +
            "  `id` int(11) NOT NULL COMMENT '主键id',\n" +
            "  `sourNodeId` int(11) DEFAULT NULL COMMENT '源节点id',\n" +
            "  `destNodeId` int(11) DEFAULT NULL COMMENT '目的节点id',\n" +
            "  `packetsCount` int(11) DEFAULT NULL COMMENT '业务流数据包数量',\n" +
            "  `arrivalRate` int(11) DEFAULT NULL COMMENT '数据包到达速率',\n" +
            "  `bandwidth` double(20,10) DEFAULT NULL COMMENT '业务流所需带宽',\n" +
            "  `oneHopFlag` binary(1) DEFAULT NULL COMMENT '是否为直接传输',\n" +
            "  `groupcount` int(11) DEFAULT NULL COMMENT '分组数量',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")
    void createTraffic();

    /**
     * 删除traffic表
     */
    @Update("drop table traffic")
    void dropTraffic();


    /**
     * 批量添加traffic
     *
     * @param traffics traffics
     */
    @Insert({
            "<script>",
            "insert into traffic(id,sourNodeId, destNodeId, packetsCount,arrivalRate,bandwidth,oneHopFlag,groupCount) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.id}, #{item.sourNodeId}, #{item.destNodeId}, #{item.packetsCount}, #{item.arrivalRate}, #{item.bandwidth}, #{item.oneHopFlag}, #{item.groupCount})",
            "</foreach>",
            "</script>"
    })
    void addTraffics(List<Traffic> traffics);


    /**
     * 获取全部traffic集合
     *
     * @return trafficList
     */
    @Select("select * from traffic ")
    @ResultType(Traffic.class)
    List<Traffic> getTrafficList();
}
