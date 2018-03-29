package personal.chencs.practice.entity;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 令牌实体
 *
 * @author: chencs
 * @date: 2018/3/28
 * @description:
 */
@Entity
public class Token {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, length = 0x20)
    private String username;
    @Column(nullable = false, length = 0x20)
    private String secretKey;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public Token() {
    }

    public Token(String username, String secretKey) {
        this.username = username;
        this.secretKey = secretKey;
        this.time = new Date();
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", time=" + time +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
