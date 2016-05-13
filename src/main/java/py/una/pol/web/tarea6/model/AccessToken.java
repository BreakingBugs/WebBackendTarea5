package py.una.pol.web.tarea6.model;

import java.io.Serializable;

/**
 * Created by codiumsa on 13/5/16.
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private User user;
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
