package net.thekingofduck.hostnamepot.controller;

import jcifs.ntlmssp.Type3Message;
import net.thekingofduck.hostnamepot.common.CommonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Project: hostnamePot
 * @ClassName: PotController
 * @Author: TheKingOfDuck
 * @Date:2021/10/19 1:20 下午
 * Github:https://github.com/TheKingOfDuck
 * When I wirting my code, only God and I know what it does. After a while, only God knows.
 */
@RestController
public class PotController {
    public static String NTLM_HEADER = "WWW-Authenticate";
    public static String NTLM_PREIX = "NTLM ";
    public static String USERNAME = "root";

    @RequestMapping(value = "/**")
    public void pot(HttpServletRequest request, HttpServletResponse response){

        try {
            String auth = request.getHeader("Authorization");
            if (auth == null) {
                response.setStatus(401);
                response.setHeader(NTLM_HEADER, "NTLM");
                return;
            }
            if (auth.startsWith(NTLM_PREIX)) {
                byte[] msg = new sun.misc.BASE64Decoder().decodeBuffer(auth.substring(5));
                if (msg[8] == 1) {
                    byte z = 0;
                    byte[] msg1 = { (byte) 'N', (byte) 'T', (byte) 'L', (byte) 'M', (byte) 'S', (byte) 'S', (byte) 'P',
                            z, (byte) 2, z, z, z, z, z, z, z, (byte) 40, z, z, z, (byte) 1, (byte) 130, z, z, z,
                            (byte) 2, (byte) 2, (byte) 2, z, z, z, z, z, z, z, z, z, z, z, z };
                    response.setHeader(NTLM_HEADER, NTLM_PREIX + new sun.misc.BASE64Encoder().encodeBuffer(msg1));
                    response.sendError(401);
                } else if (msg[8] == 3) {
                    Type3Message info = new Type3Message(msg);
                    System.out.println(info);
                    CommonUtils.info(request.getRemoteAddr(),info.toString());
                    if (USERNAME.equals(info.getUser())){
                        response.getWriter().println("index");
                        response.getWriter().flush();
                        response.getWriter().close();
                    }
                }
            }
        }catch (Exception e){}
    }
}
