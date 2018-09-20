package com.kaituo.pms.service;

import com.kaituo.pms.bean.Position;

import java.util.List;

public interface PositionService {
   List<Position> getPositionNameBydeptId(int deptId);

   int addPositons(List<String>  positionArray,int deptId);

   List<String> checkPositions(List<String>  positionArray,int deptId);
}
