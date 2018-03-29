package personal.chencs.practice.token;

/**
 * 时间型令牌接口
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public interface TOTP {

    /**
     * 生成动态口令
     *
     * @param offset 时间偏移
     * @param secretKey 种子密钥
     * @return 动态口令
     */
    String generateOTP(int offset, byte[] secretKey);

}
