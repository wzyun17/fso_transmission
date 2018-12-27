package com.neuln.fso.transmission.dao;


import com.neuln.fso.transmission.entity.FsoNode;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 节点的dao
 *
 * @author wzYun
 * @date 2018-12-25 13:14:07
 */
@Mapper
public interface FsoNodeDao {

    /**
     * 删除fso_node表
     */
    @Update("drop table fso_node")
    void dropFsoNode();


    /**
     * 创建fso_node表
     */
    @Update("CREATE TABLE `fso_node` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '节点id',\n" +
            "  `xPosition` float(20) DEFAULT NULL COMMENT '节点x轴坐标',\n" +
            "  `yPosition` float(20) DEFAULT NULL COMMENT '节点y轴坐标',\n" +
            "  `transceiverCount` int(10) DEFAULT NULL COMMENT '节点tr数量',\n" +
            "  `occupiedTRCount` int(10) DEFAULT NULL COMMENT '使用中的tr数量',\n" +
            "  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")
    void createFsoNode();

    /**
     * 批量添加fso节点
     *
     * @param fsoNodes fsoNodes
     */
    @Insert({
            "<script>",
            "insert into fso_node(xPosition, yPosition, transceiverCount, occupiedTRCount) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.xPosition}, #{item.yPosition}, #{item.transceiverCount}, #{item.occupiedTRCount})",
            "</foreach>",
            "</script>"
    })
    void addFsoNodes(List<FsoNode> fsoNodes);


    /**
     * 批量获取所有的Fso节点
     *
     * @return FsoNode集合
     */
    @Select("select * from fso_node")
    @ResultType(FsoNode.class)
    List<FsoNode> getFsoNodeList();
}
