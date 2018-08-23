package com.kaituo.pms.service;

import com.kaituo.pms.bean.Position;

import java.util.List;

public interface PositionService {
   List<Position> getPositionNameBydeptId(int deptId);

   int addPositons(String[] positionArray,int deptId);
}
