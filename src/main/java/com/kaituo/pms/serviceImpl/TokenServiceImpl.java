package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Token;
import com.kaituo.pms.bean.TokenExample;
import com.kaituo.pms.dao.TokenMapper;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
   /*     Integer userId = JwtToken.getUserId(token);*/
        if(null==token||token.isEmpty()){
            return null;
        }
        TokenExample tokenExample = new TokenExample();
        tokenExample.createCriteria().andTokenEqualTo(token);
        List<Token> tokens = tokenMapper.selectByExample(tokenExample);
        if(null == tokens||tokens.size()==0){
            return null;
        }
        Token token1 = tokens.get(0);
        if(null == token1){
            return null;
        }
        long time = token1.getFailureTime().getTime()+24*60*60*1000;
        if (time < System.currentTimeMillis()){
            delectToken(token);
            return null;
        }else {

            upToken(Token.getNewToken(token1.getUserId(),token1.getToken()));
            return token1;
        }
    }

    @Override
    public int delectToken(String token) {
        if(null==token||token.isEmpty()){
            return 0;
        }
        TokenExample tokenExample = new TokenExample();
        tokenExample.createCriteria().andTokenEqualTo(token);
        List<Token> tokens = tokenMapper.selectByExample(tokenExample);
        if(null == tokens||tokens.size()==0){
            return 0;
        }
        Token token1 = tokens.get(0);
        return tokenMapper.deleteByPrimaryKey(token1.getUserId());
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
