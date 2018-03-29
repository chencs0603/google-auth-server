package personal.chencs.practice.token;

/**
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public interface HOTP {

    String generateOTP(long counter, int offset, byte[] secretKey);
    
}
