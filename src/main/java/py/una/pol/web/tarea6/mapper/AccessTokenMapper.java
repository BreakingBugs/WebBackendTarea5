package py.una.pol.web.tarea6.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import py.una.pol.web.tarea6.model.AccessToken;

import java.util.List;

/**
 * Created by codiumsa on 13/5/16.
 */
public interface AccessTokenMapper {
    AccessToken getAccessToken(int id);
    List<AccessToken> getAccessTokens();

    @Insert("INSERT INTO access_token(token, user_id) VALUES (#{token}, #{user.id})")
    void insertAccessToken(AccessToken at);

    @Update("UPDATE access_token SET token=#{token}, user_id=#{user.id} WHERE id=#{id}")
    void updateAccessTokeen(AccessToken at);

    @Delete("DELETE FROM access_token WHERE id=#{id}")
    void deleteAccessToken(int id);
}
