package personal.chencs.practice.token;

/**
 *
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public interface Token {

    String generateOTP(byte[] info, byte[] secretKey);

}
