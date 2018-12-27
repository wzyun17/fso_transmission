package com.neuln.fso.transmission.dao;

import com.neuln.fso.transmission.entity.TimeSlot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * timeSlot的Dao
 *
 * @author wzYun
 * @date 2018-12-27 09:33:23
 */
@Mapper
public interface TimeSlotDao {


    /**
     * 创建time_slot表
     */
    @Update("CREATE TABLE `time_slot` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `timeSlotId` int(11) DEFAULT NULL,\n" +
            "  `trafficId` int(11) DEFAULT NULL,\n" +
            "  `packetsInSlotCount` int(11) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")
    void createTimeSlot();

    /**
     * 删除time_slot表
     */
    @Update("drop table time_slot")
    void dropTimeSlot();


    /**
     * 批量添加timeslot
     *
     * @param timeSlots timeSlots
     */
    @Insert({
            "<script>",
            "insert into time_slot(timeSlotId, trafficId, packetsInSlotCount) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.timeSlotId}, #{item.trafficId}, #{item.packetsInSlotCount})",
            "</foreach>",
            "</script>"
    })
    void addTimeSlots(List<TimeSlot> timeSlots);
}
