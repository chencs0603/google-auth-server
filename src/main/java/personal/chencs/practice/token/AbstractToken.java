package personal.chencs.practice.token;

/**
 * 定义OTP计算的抽象流程
 * OTP = truncate(hmac(info, secretKey))
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public abstract class AbstractToken implements Token {

    // TODO:配置文件设置算法、口令长度等
    private String algorithmName = "SHA1";

    @Override
    public String generateOTP(byte[] info, byte[] secretKey) {
        byte[] hmac = hmac(info, secretKey, this.algorithmName);
        String result = truncator(hmac, 6);

        return result;
    }

    protected abstract byte[] hmac(byte[] info, byte[] secretKey, String algorithmName);

    protected abstract String truncator(byte[] hmac, int returnDigit);

}
