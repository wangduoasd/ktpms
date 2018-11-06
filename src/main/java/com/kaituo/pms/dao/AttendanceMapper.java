package com.kaituo.pms.dao;



import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.bean.AttendanceExample;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AttendanceMapper {
    int countByExample(AttendanceExample example);

    int deleteByExample(AttendanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    List<Attendance> selectByExampleWithBLOBs(AttendanceExample example);

    List<Attendance> selectByExample(AttendanceExample example);

    Attendance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    int updateByExampleWithBLOBs(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    int updateByExample(@Param("record") Attendance record, @Param("example") AttendanceExample example);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKeyWithBLOBs(Attendance record);

    int updateByPrimaryKey(Attendance record);

    List<Attendance> selectAll();
    int  updateDeductintegral(@Param("id")int id,@Param("deductintegral")int deductintegral);
}