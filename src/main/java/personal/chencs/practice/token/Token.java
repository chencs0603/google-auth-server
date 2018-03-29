package personal.chencs.practice.token;

/**
 * 令牌接口
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public interface Token {

    /**
     * 生成动态口令
     *
     * @param info 输入因子，每种令牌的输入因子各不相同
     * @param secretKey 种子密钥
     * @return 动态口令
     */
    String generateOTP(byte[] info, byte[] secretKey);

}
