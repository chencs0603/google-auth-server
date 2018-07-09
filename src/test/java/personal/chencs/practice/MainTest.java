package personal.chencs.practice;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

/**
 * @author: chencs
 * @date: 2018/5/24
 * @description:
 */
public class MainTest {
    public static void main(String[] args) throws Exception {
        String[] hex = {"30", "0f", "21ad", "04ceb3", "614F54A8", "7660E27ACF", "75D2D2C0478D", "6473D8AB0BB0EA", "2F89E4FDF5857CED",
                "AE9824551841F2CFA6", "7EDD5B44161AF16E66E1", "D62DE4C8194A7F9A891D3E", "6C4357B2478E01AD7CED60CCBE87BBAC", "C02530AA0189594F1A3586A0B1E475A48479634C"};
        for (int i = 0; i < hex.length; i++) {
            byte[] bytes = Hex.decodeHex(hex[i].toCharArray());
            System.out.println(hex[i] + "-->" + new String(new Base32(false, (byte) 0).encode(bytes)));
        }



        // base32->hex
        System.out.println(Hex.encodeHexString(new Base32(false, (byte) 0).decode("SPBGM53VC4UTED2IZRCCO7PNTHK2VB72")));
    }
}
