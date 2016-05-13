package py.una.pol.web.tarea6.controller;

import org.apache.ibatis.session.SqlSession;
import py.una.pol.web.tarea6.initialization.MyBatisSingleton;
import py.una.pol.web.tarea6.mapper.AccessTokenMapper;
import py.una.pol.web.tarea6.model.AccessToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by codiumsa on 13/5/16.
 */
@Stateless
public class AccessTokenController {
    @Inject
    private MyBatisSingleton myBatis;

    public List<AccessToken> getAccessTokens() {
        List<AccessToken> accessTokens;
        SqlSession session = myBatis.getFactory().openSession();
        try {
            AccessTokenMapper mapper = session.getMapper(AccessTokenMapper.class);
            accessTokens = mapper.getAccessTokens();
        } finally {
            session.close();
        }
        return accessTokens;
    }

    public void addAccessToken(AccessToken c) {
        SqlSession session = myBatis.getFactory().openSession();
        try {
            AccessTokenMapper mapper = session.getMapper(AccessTokenMapper.class);
            mapper.insertAccessToken(c);
        }
        finally {
            session.close();
        }
    }

    public AccessToken getAccessToken(Integer id) {
        AccessToken accessToken;
        SqlSession session = myBatis.getFactory().openSession();
        try {
            AccessTokenMapper mapper = session.getMapper(AccessTokenMapper.class);
            accessToken = mapper.getAccessToken(id);
        } finally {
            session.close();
        }
        return accessToken;
    }

    public void removeAccessToken(final Integer id) {
        SqlSession session = myBatis.getFactory().openSession();
        try {
            AccessTokenMapper mapper = session.getMapper(AccessTokenMapper.class);
            mapper.deleteAccessToken(id);
        }
        finally {
            session.close();
        }
    }
}
