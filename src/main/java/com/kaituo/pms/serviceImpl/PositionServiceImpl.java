package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Position;
import com.kaituo.pms.bean.PositionExample;
import com.kaituo.pms.dao.PositionMapper;
import com.kaituo.pms.service.PositionService;
import com.kaituo.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: PositionServiceImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/8/21 002115:53
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    UserService userService;
    @Override
    public List<Position> getPositionNameBydeptId(int deptId) {
        PositionExample positionExample = new PositionExample();
        positionExample.createCriteria().andDeptIdEqualTo(deptId);
        return positionMapper.selectByExample(positionExample);
    }
    @Override

    public int addPositons(List<String>  positionArray,int deptId) {
 /*       positionArray.containsAll()
        ArrayList<String> lists = new ArrayList<>();
        for(Position p:positions){
            lists.add(p.getPositionName());
        }
        PositionExample positionExample2 = new PositionExample();
        positionExample2.createCriteria().andDeptPositionIdNotIn(lists);*/
        Position position = new Position();
        for (String p:positionArray ) {
            position.setPositionName(p);
            position.setDeptId(deptId);
            positionMapper.insertSelective(position);
        }
        return 1;
    }

    @Override
    public List<String> checkPositions(List<String> positionArray,int deptId) {
        PositionExample positionExample = new PositionExample();
        positionExample.createCriteria().andDeptIdEqualTo(deptId);
        List<Position> positions = positionMapper.selectByExample(positionExample);
        for (Position p:positions){

       if(!positionArray.contains(p.getPositionName())){
           int num = userService.findUserByDeptPositionId(p.getDeptPositionId());
           if(num==0){positionMapper.deleteByPrimaryKey(p.getDeptPositionId());}
           else {
               return null;
           }
       }
            positionArray.remove(p.getPositionName());
        }
/*        for(String p1:positionArray){
            for(Position p:positions){
                if(positions.contains(p.getPositionName())){
                    positionArray.remove(p1);
                }

            }
        }*/
        return positionArray;
    }
}
