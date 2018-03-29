package personal.chencs.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.chencs.practice.entity.Authenticator;

/**
 * 认证器DAO接口
 *
 * @author: chencs
 * @date: 2018/3/28
 * @description:
 */
public interface AuthenticatorRepository extends JpaRepository<Authenticator, Long> {
}
