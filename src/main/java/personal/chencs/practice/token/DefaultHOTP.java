package personal.chencs.practice.token;

/**
 * 事件型OTP实现
 * 参考RFC4226
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public class DefaultHOTP extends DefaultToken implements HOTP {

    @Override
    public String generateOTP(long counter, int offset, byte[] secretKey) {
        byte[] info = getInfo(counter, offset);
        String result = generateOTP(info, secretKey);

        return result;
    }

    protected byte[] getInfo(long counter, int offset) {
        byte[] result = BytesUtils.longToBytes(counter + offset, true);

        return result;

    }

}
