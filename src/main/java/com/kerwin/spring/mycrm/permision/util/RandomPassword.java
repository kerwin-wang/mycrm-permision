package com.kerwin.spring.mycrm.permision.util;

import java.util.Random;

/**
 * 随机生成密码
 *
 * @className: RandomPassword
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-05 13:48
 */
public class RandomPassword
{
    /** 随机字符数组 */
    private static final String[] WORD = {"a", "b", "c", "d", "e", "F", "g", "h", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "!", "@", "#", ".", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    /** 随机数字数组 */
    private static final String[] NUM = {"2", "3", "4", "5", "6", "7", "8", "9"};

    /** 密码最小值 */
    private static final int MIN_LENGTH = 8;

    /** 密码随机增量最小值 */
    private static final int MIN_INCREMENT = 0;

    /**
     * 功能描述:
     * 〈随机生成默认基准长度和随机增量的密码〉
     *
     * @param
     * @return : java.lang.String
     * @author : d.w
     * @date : 2019/09/05 13:58
     */
    public static String randomPassword()
    {
        return randomPasswordWithLength(MIN_LENGTH, MIN_INCREMENT);
    }

    /**
     * 功能描述:
     * 〈根据基准长度和随机增量长度生成随机密码〉
     *
     * @param baseLength 基准长度
     * @param increment  随机增量长度
     * @return : java.lang.String
     * @author : d.w
     * @date : 2019/09/06 10:52
     */
    public static String randomPasswordWithLength(int baseLength, int increment)
    {
        StringBuffer buffer = new StringBuffer(16);
        Random random = new Random();
        baseLength = baseLength < 8 ? MIN_LENGTH : baseLength;
        increment = increment < 0 ? MIN_INCREMENT : increment;
        // 设置密码长度，至少8位字符
        int length = increment == 0 ? baseLength : random.nextInt(increment) + baseLength;
        // 设置密码生成标志位，用于区分获取字符或这数字
        boolean flag = false;
        for (int i = 0; i < length; i++)
        {
            if (flag)
            {
                buffer.append(WORD[random.nextInt(WORD.length)]);
            }
            else
            {
                buffer.append(NUM[random.nextInt(NUM.length)]);
            }
            flag = !flag;
        }

        return buffer.toString();
    }

    public static void main(String[] args)
    {
//        System.out.println(randomPasswordWithLength(18, 0));
//        System.out.println(randomPasswordWithLength(10, 6));
//        System.out.println(randomPasswordWithLength(8, 3));
//        System.out.println(randomPassword());

        int a = 15;
        printBinary(a);
        a = a >> 1;
        printBinary(a);
        System.out.println(a);
    }

    private static void printBinary(int a){
        System.out.println(Integer.toBinaryString(a));
    }

}
