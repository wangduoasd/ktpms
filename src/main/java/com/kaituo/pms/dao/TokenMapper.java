package com.kaituo.pms.dao;

import com.kaituo.pms.bean.Token;
import com.kaituo.pms.utils.TokenMap;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Mapper
public interface TokenMapper {
    @Insert("insert into p_token(user_id,token,failure_time) values(#{userId},#{token},#{failureTime})")
    int addToken(Token token);
    @Update("update p_token set failure_time=#{failureTime} where token=#{token}")
    int upToken(Token token);
    @Select("select user_id,token,failure_time from p_token where token=#{token}")
    Token selectUserIdByToken(String token);
    @Delete("delete from p_token where token=#{token}")
    int delectToken(String token);
}
