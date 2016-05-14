package py.una.pol.web.tarea6.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import py.una.pol.web.tarea6.model.User;

import java.util.List;

/**
 * Created by codiumsa on 13/5/16.
 */
public interface UserMapper {
    User getUser(int id);
    User getUserByUsername(String username);
    List<User> getUsers();

    @Insert("INSERT INTO app_user(username, password, role) VALUES (#{username}, #{password}, #{role.value})")
    void insertUser(User u);

    @Update("UPDATE app_user SET username=#{username}, password=#{password}, role=#{role.value} WHERE id=#{id}")
    void updateUser(User u);

    @Delete("DELETE FROM app_user WHERE id=#{id}")
    void deleteUser(int id);
}
