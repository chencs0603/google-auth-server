package personal.chencs.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.chencs.practice.service.AuthenticatorService;

import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author: chencs
 * @date: 2018/3/28
 * @description:
 */
@Controller
public class AuthenticatorController {

    @Autowired
    private AuthenticatorService tokenService;

    @RequestMapping(value = "/generateQrcode", method = RequestMethod.POST)
    public void generateQrcode(HttpServletResponse response, String username, String hexSecretKey) throws Exception {
        // TODO:异常情况处理，检测用户名输入不合法

        // 控制浏览器不要缓存
        response.setDateHeader("expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        // 写入response
        response.setHeader("Content-Type", "image/png");
        tokenService.generateQrcode(username, hexSecretKey, response.getOutputStream());
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public String authenticate(String username, String password) {
        boolean result = tokenService.authenticator(username, password);
        if (result) {
            return "认证成功";
        } else {
            return "认证失败";
        }
    }

}
