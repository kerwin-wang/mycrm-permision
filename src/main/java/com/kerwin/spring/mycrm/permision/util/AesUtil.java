package com.kerwin.spring.mycrm.permision.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES加解密util,可以用作数据库的密码加解密
 *
 * @className: AesUtil
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-05 15:22
 */
public class AesUtil
{
    /**
     * 功能描述:
     * 〈加密〉
     * 1.构造密钥生成器
     * 2.根据salt规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     *
     * @param salt    盐
     * @param content 加密内容
     * @return : java.lang.String
     * @author : d.w
     * @date : 2019/09/05 15:36
     */
    public static String aesEncode(String salt, String content)
    {
        try
        {
            SecretKey key = getSecretKey(salt);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes(StandardCharsets.UTF_8);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            //11.将字符串返回
            return new BASE64Encoder().encode(byteAES);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    /**
     * 功能描述:
     * 〈解密〉
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     *
     * @param salt    盐
     * @param content 需要解密的内容
     * @return : java.lang.String
     * @author : d.w
     * @date : 2019/09/05 15:47
     */
    public static String aesDecode(String salt, String content)
    {
        try
        {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            SecretKey key = getSecretKey(salt);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byteContent = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byteDecode = cipher.doFinal(byteContent);
            return new String(byteDecode, StandardCharsets.UTF_8);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    /**
     * 功能描述:
     * 〈通过salt获取密钥〉
     *
     * @param salt 盐
     * @return : javax.crypto.SecretKey
     * @author : d.w
     * @date : 2019/09/05 15:35
     */
    private static SecretKey getSecretKey(String salt) throws NoSuchAlgorithmException
    {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        keygen.init(128, new SecureRandom(salt.getBytes()));
        //3.产生原始对称密钥
        SecretKey originalKey = keygen.generateKey();
        //4.获得原始对称密钥的字节数组
        byte[] raw = originalKey.getEncoded();
        //5.根据字节数组生成AES密钥
        return new SecretKeySpec(raw, "AES");
    }

    /**
     * 功能描述:
     * 〈随机生成盐值，并封装为对象，返回〉
     *
     * @param content 需要加密的内容
     * @return : com.kerwin.spring.mycrm.permision.util.AesUtil.InnerAesDomain
     * @author : d.w
     * @date : 2019/09/06 10:44
     */
    public static InnerAesDomain aesEncode(String content)
    {
        // 用domain 内部类封装加密后密文和盐值
        InnerAesDomain domain = new InnerAesDomain();

        // 通过randomPassword类生成盐，用以加密password
        String salt = RandomPassword.randomPasswordWithLength(16,0);
        String cipherText = aesEncode(salt, content);
        domain.setSalt(salt);
        domain.setCipherText(cipherText);
        return domain;
    }

    /**
     * 功能描述:
     * 〈测试加密，key若为scanner输入时，须为16字节的倍数〉
     *
     * @param args
     * @return : void
     * @author : d.w
     * @date : 2019/09/06 9:40
     */
    public static void main(String[] args)
    {
//        String content = "1.RSA是基于大数因子分解难题。目前各种主流计算机语言都支持RSA算法的实现2.java6支持RSA算法3.RSA算法可以用于数据加密和数字签名4.RSA算法相对于DES/AES等对称加密算法，他的速度要慢的多5.总原则：公钥加密，私钥解密 / 私钥加密，公钥解密二、模型分析RSA算法构建密钥对简单的很，这里我们还是以甲乙双方发送数据为模型1.甲方在本地构建密钥对（公钥+私钥），并将公钥公布给乙方2.甲方将数据用私钥进行加密，发送给乙方3.乙方用甲方提供的公钥对数据进行解密";
        String content = RandomPassword.randomPassword();
        InnerAesDomain domain = aesEncode(content);
        System.out.println(domain.toString());

        String s1 = aesDecode(domain.salt, domain.cipherText);
        System.out.println(s1);

        //        /*
        //         * 加密
        //         */
        //        System.out.println("使用AES对称加密，请输入加密的规则");
        //        String s = aesUtil.aesEncode(key, content);
        //        System.out.println("根据输入的规则" + key + "加密后的密文是:" + s);
        //
        //        /*
        //         * 解密
        //         */
        //        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
        //        System.out.println("根据输入的规则" + key + "解密后的明文是:" + aesUtil.aesDecode(key, s));
    }

    /**
     * AES内部类，用以封装加密后内容和盐值
     *
     * @className: InnerAesDomain
     * @version: v1.0.0
     * @author: d.w
     * @date: 2019-09-05 15:22
     */
    @Getter
    @Setter
    @ToString
    static class InnerAesDomain
    {
        private String cipherText;

        private String salt;

    }

}
