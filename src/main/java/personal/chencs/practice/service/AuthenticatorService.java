package personal.chencs.practice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import personal.chencs.practice.entity.Authenticator;
import personal.chencs.practice.repository.AuthenticatorRepository;
import personal.chencs.practice.token.DefaultTOTP;
import personal.chencs.practice.token.TOTP;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证器业务层接口
 *
 * @author: chencs
 * @date: 2018/3/28
 * @description:
 */
@Service
public class AuthenticatorService {

    // TODO：日志记录、异常处理

    // TODO:放入配置文件中
    private static int WIDTH = 300;
    private static int HEIGHT = 300;
    private static String IMAGE_FROMAT = "png";

    @Autowired
    private AuthenticatorRepository authenticatorRepository;

    @Autowired
    private TOTP token;

    @Bean
    public TOTP getToken() {
        return new DefaultTOTP();
    }

    /**
     * 生成种子密钥，并以二维码形式返回
     *
     * @param username 用户名
     * @return 二维码的字节流
     */
    public void generateQrcode(String username, String hexSecretKey, OutputStream outputStream) {
        // 种子密钥为空，则随机生成种子密钥
        if (StringUtils.isBlank(hexSecretKey)) {
            hexSecretKey = generateSecretKey();
        }
        String secretKey = encodeSecretKey(hexSecretKey);
        // google authenticator客户端可以识别的字符串（包含用户名和种子）
        String content = generateContent4Qrcode(username, secretKey);
        // 生成二维码，以字节流输出
        createQrcode(content, outputStream);
        // 保存用户信息
        authenticatorRepository.save(new Authenticator(username, secretKey));
    }

    /**
     * 动态口令认证
     *
     * @param username 用户名
     * @param password 动态口令
     * @return 认证成功返回true，否则返回false
     */
    public boolean authenticator(String username, String password) {
        Authenticator authenticator = authenticatorRepository.findByUsername(username);
        if (null == authenticator) {
            return false;
        }
        String base32SecretKey = authenticator.getSecretKey();
        byte[] secretKey = new Base32().decode(base32SecretKey);

        String validPassword = token.generateOTP(0, secretKey);
        if (validPassword.equals(password)) {
            return true;
        }
        for (int i = 1; i < 2; i++) {
            validPassword = token.generateOTP(i, secretKey);
            if (validPassword.equals(password)) {
                return true;
            }
            validPassword = token.generateOTP(-i, secretKey);
            if (validPassword.equals(password)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 生成种子密钥，以base32编码的字符串输出
     *
     * @return 种子密钥的base32编码
     */
    private String generateSecretKey() {
        // 产生种子密钥的16进制字符串
        String hexSecretKey = RandomStringUtils.random(20, "0123456789ABCDEF");

        return hexSecretKey;
    }

    /**
     * 16进制字符串转成成base32
     *
     * @param hexSecretKey 16进制字符串
     * @return base32字符串
     */
    private String encodeSecretKey(String hexSecretKey) {
        try {
            // 种子密钥的16进制解码
            byte[] secretKey = Hex.decodeHex(hexSecretKey.toCharArray());
            // 种子密钥base32编码
            String base32SecretKey = new String(new Base32().encode(secretKey));

            return base32SecretKey;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成google authenticator客户端可以识别的字符串
     *
     * @param username 用户名
     * @param secretKey 种子密钥
     * @return 字符串
     */
    private String generateContent4Qrcode(String username, String secretKey) {
        String format = "otpauth://totp/%s?secret=%s";
        return String.format(format, username, secretKey);

    }

    /**
     * 生成二维码，以字符流输出
     *
     * @param content 二维码中的内容
     * @return 二维码的字符流输出
     */
    private void createQrcode(String content, OutputStream outputStream) {
        try {
            // 二维码的编码、容错率和边距
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 2);
            // 生成矩阵
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            MatrixToImageWriter.writeToStream(matrix, IMAGE_FROMAT, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
