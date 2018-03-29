package personal.chencs.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.chencs.practice.entity.Token;

/**
 * 令牌DAO接口
 *
 * @author: chencs
 * @date: 2018/3/28
 * @description:
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
}
