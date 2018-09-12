package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.TokenMapper;
import com.kaituo.pms.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
        return tokenMapper.addToken(token);
    }

    @Override
    public int upToken(Token token) {
        return tokenMapper.upToken(token);
    }
   @Override
    public Token selectUserIdByToken(String token) {
        Token tokenEntity = tokenMapper.selectUserIdByToken(token);
        if (null == tokenEntity){
            return null;
        }
        long time = tokenEntity.getFailureTime().getTime();
        if (time <= System.currentTimeMillis()){
            delectToken(token);
            return null;
        }else {
            return tokenEntity;
        }
    }

    @Override
    public int delectToken(String token) {
        return tokenMapper.delectToken(token);
    }
}
