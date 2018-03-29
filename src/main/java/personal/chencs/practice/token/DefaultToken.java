package personal.chencs.practice.token;

import org.apache.commons.codec.digest.HmacUtils;

/**
 * hmac和truncate的默认实现
 * 参考RFC4226
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public class DefaultToken extends AbstractToken {

    @Override
    protected byte[] hmac(byte[] info, byte[] secretKey, String algorithmName) {
        byte[] hmac = null;

        switch (algorithmName) {
            case "SHA1":
                hmac = HmacUtils.hmacSha1(secretKey, info);
                break;
            case "SHA256":
                hmac = HmacUtils.hmacSha256(secretKey, info);
                break;
            case "SHA512":
                hmac = HmacUtils.hmacSha512(secretKey, info);
                break;
            default:
                throw new IllegalArgumentException("Illegal algorithmName:" + algorithmName);
        }

        return hmac;
    }

    @Override
    protected String truncator(byte[] hmac, int returnDigit) {
        // 验证returnDigit的取值范围
        if (0x04 > returnDigit || 0x08 < returnDigit) {
            throw new IllegalArgumentException("Illegal returnDigit:" + returnDigit);
        }

        // 取最后一个字节的低4bit作为索引
        int offset = hmac[hmac.length - 0x01] & 0x0F;
        // 从offset开始取4字节，并去掉最高位的符号位
        int binary = ((hmac[offset] & 0x7F) << 24) |
                 ((hmac[offset + 1] & 0xFF) << 16) |
                  ((hmac[offset + 2] & 0xFF) << 8) |
                      ((hmac[offset + 3] & 0xFF) );

        final int[] digitsPower = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        // 取模操作
        int num = binary % digitsPower[returnDigit];

        StringBuilder result = new StringBuilder(Integer.toString(num));
        // 位数不够在前面补0直到returnDigit位
        while (result.length() < returnDigit) {
            result.insert(0, "0");
        }

        return result.toString();
    }

}
