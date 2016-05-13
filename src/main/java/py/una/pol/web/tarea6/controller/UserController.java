package py.una.pol.web.tarea6.controller;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.una.pol.web.tarea6.exceptions.OutOfStockException;
import py.una.pol.web.tarea6.initialization.MyBatisSingleton;
import py.una.pol.web.tarea6.mapper.*;
import py.una.pol.web.tarea6.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by codiumsa on 13/5/16.
 */
@Stateless
public class UserController {
    @Inject
    private MyBatisSingleton myBatis;

    public List<User> getUsers() {
        List<User> users;
        SqlSession session = myBatis.getFactory().openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            users = mapper.getUsers();
        } finally {
            session.close();
        }
        return users;
    }

    public void addUser(User c) {
        c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
        SqlSession session = myBatis.getFactory().openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insertUser(c);
        }
        finally {
            session.close();
        }
    }

    public User getUser(Integer id) {
        User user;
        SqlSession session = myBatis.getFactory().openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            user = mapper.getUser(id);
        } finally {
            session.close();
        }
        return user;
    }

    public User updateUser(Integer id, User userWithChanges) {
        SqlSession session = myBatis.getFactory().openSession();
        User c = getUser(id);
        if(userWithChanges.getUsername() != null && userWithChanges.getUsername().compareTo(c.getUsername()) != 0) {
            c.setUsername(userWithChanges.getUsername());
        }
        if(userWithChanges.getPassword() != null && userWithChanges.getPassword().compareTo(c.getPassword()) != 0) {
            c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
        }
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.updateUser(c);
        }
        finally {
            session.close();
        }
        return c;
    }

    public void removeUser(final Integer id) {
        SqlSession session = myBatis.getFactory().openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.deleteUser(id);
        }
        finally {
            session.close();
        }
    }
}
