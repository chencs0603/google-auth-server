package personal.chencs.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.chencs.practice.service.TokenService;

import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author: chencs
 * @date: 2018/3/28
 * @description:
 */
@Controller
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/generateQrcode")
    public void generateQrcode(HttpServletResponse response, String username) throws Exception {
        // TODO:异常情况处理，检测用户名输入不合法

        // 控制浏览器不要缓存
        response.setDateHeader("expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        // 写入response
        response.setHeader("Content-Type", "image/png");
        tokenService.generateQrcode(username, response.getOutputStream());
    }

}
