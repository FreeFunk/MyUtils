package com.edgedo.decrypt;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:Qiutianzhu
 * @Date 2022-04-20 13:55
 * @Description: 文本密码解密
 */
public class DecryptUtils {



    public static void main(String[] args){
        String publicPassword = "pushiAi123";
        String password = "AjiaoNiu13";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(publicPassword);
        //加密
//        String jiami = basicTextEncryptor.encrypt(password);
//        System.out.println(jiami);
        //解密
//        String jiami1  = "kS3A+JG9f3vkwueg8cD4YaGiNxe/H5Cj";
        String jiami2 = "5DMHNfyrENi2h6TK9xzcFofDHntgmfrE";
        String jiemi = basicTextEncryptor.decrypt(jiami2);
    }


}
