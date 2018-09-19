package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.TokenMapper;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 张金行
 * @version 1.0
 * @Title: TokenServiceImpl
 * @ProjectName pms
 * @Description:
 * @date 2018/9/12 001220:36
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenMapper tokenMapper;
    @Override
    public int addToken(Token token) {
        return tokenMapper.insertSelective(token);
    }

    @Override
    public int upToken(Token token) {
        return tokenMapper.updateByPrimaryKeySelective(token);
    }
    @Override
    public Token selectUserIdByToken(String token) {
        Integer userId = JwtToken.getUserId(token);
        if(null == userId){
            return null;
        }
        Token tokenEntity = tokenMapper.selectByPrimaryKey(userId);
        if (null == tokenEntity){
            return null;
        }
        long time = tokenEntity.getFailureTime().getTime()+24*60*60*1000;
        if (time < System.currentTimeMillis()){
            delectToken(token);
            return null;
        }else {

            upToken(Token.getNewToken(tokenEntity.getUserId(),tokenEntity.getToken()));
            return tokenEntity;
        }
    }

    @Override
    public int delectToken(String token) {
        int userId = JwtToken.getUserId(token);
        return tokenMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int delectToken(Integer userId) {
        return tokenMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public boolean haveToken(Integer userId) {
        if (null!=tokenMapper.selectByPrimaryKey(userId)){
            return true;
        }
        return false;
    }
}
