package personal.chencs.practice.token;

import personal.chencs.util.BytesUtils;

/**
 * 时间型OTP实现
 * 参考RFC6238
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public class DefaultTOTP extends DefaultToken implements TOTP {

    private int timeStep = 30;

    @Override
    public String generateOTP(int offset, byte[] secretKey) {
        byte[] info = getInfo(timeStep, offset);
        String result = generateOTP(info, secretKey);

        return result;
    }

    /**
     * 计算输入因子
     *
     * @param timeStep 时间步长，即周期
     * @param offset 时间偏移（单位是周期）
     * @return 输入因子
     */
    protected byte[] getInfo(int timeStep, int offset) {
        long timestamp = System.currentTimeMillis();
        long timeNum = timestamp/timeStep/1000 + offset;

        byte[] result = BytesUtils.longToBytes(timeNum, true);

        return result;

    }

}
