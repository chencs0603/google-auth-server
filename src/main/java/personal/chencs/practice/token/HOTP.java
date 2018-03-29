package personal.chencs.practice.token;

/**
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public interface HOTP {

    /**
     * 生成动态口令
     *
     * @param counter 计数因子
     * @param offset 计数因子偏移
     * @param secretKey 种子密钥
     * @return 动态口令
     */
    String generateOTP(long counter, int offset, byte[] secretKey);
    
}
