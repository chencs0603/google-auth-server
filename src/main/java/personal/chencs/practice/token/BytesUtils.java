package personal.chencs.practice.token;

/**
 * byte数组工具类
 *
 * @author: chencs
 * @date: 2018/3/29
 * @description:
 */
public class BytesUtils {

    /**
     * long型整数转成8字节byte数组
     *
     * @param num long型整数
     * @param bigEndian true为大端，否则为小端
     * @return byte数组
     */
    public static byte[] longToBytes(long num, boolean bigEndian) {
        byte[] result = new byte[0x08];
        if (bigEndian) {
            // 大端模式
            result[0] = (byte) (num >>> 56);
            result[1] = (byte) (num >>> 48);
            result[2] = (byte) (num >>> 40);
            result[3] = (byte) (num >>> 32);
            result[4] = (byte) (num >>> 24);
            result[5] = (byte) (num >>> 16);
            result[6] = (byte) (num >>> 8);
            result[7] = (byte) (num);
        } else {
            // 小端模式
            result[7] = (byte) (num >>> 56);
            result[6] = (byte) (num >>> 48);
            result[5] = (byte) (num >>> 40);
            result[4] = (byte) (num >>> 32);
            result[3] = (byte) (num >>> 24);
            result[2] = (byte) (num >>> 16);
            result[1] = (byte) (num >>> 8);
            result[0] = (byte) (num);
        }
        return result;
    }
}
