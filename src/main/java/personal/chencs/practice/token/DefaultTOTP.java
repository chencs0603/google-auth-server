package personal.chencs.practice.token;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

/**
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
@Component
public class DefaultTOTP extends DefaultToken implements TOTP {

    private int timeStep = 30;

    @Override
    public String generateOTP(int offset, byte[] secretKey) {
        byte[] info = getInfo(timeStep, offset);
        String result = generateOTP(info, secretKey);

        return result;
    }

    protected byte[] getInfo(int timeStep, int offset) {
        long timestamp = System.currentTimeMillis();
        long timeNum = timestamp/timeStep/1000 + offset;

        byte[] result = BytesUtils.longToBytes(timeNum, true);

        return result;

    }

}
