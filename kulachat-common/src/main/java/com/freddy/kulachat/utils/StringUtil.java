package com.freddy.kulachat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author FreddyChen
 * @name
 * @date 2020/09/02 10:30
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return null == str || str.toString().trim().equals("");
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null) {
            return s2 == null;
        }

        return s1.equals(s2);
    }

    /**
     * 判断手机号是否合法
     * 目前已支持的匹配号段
     * 中国电信号段
     *  133、149、153、173、177、180、181、189、199
     * 中国联通号段
     *  130、131、132、145、155、156、166、175、176、185、186
     * 中国移动号段
     *  134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     * 其他号段
     *  14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     * 虚拟运营商
     *  电信：1700、1701、1702
     *  移动：1703、1705、1706
     *  联通：1704、1707、1708、1709、171
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static void main(String[] args) {
        String phone = "17708256897";
        System.out.println(StringUtil.isPhone(phone));
        phone = "13729189670";
        System.out.println(StringUtil.isPhone(phone));
        phone = "14788545775";
        System.out.println(StringUtil.isPhone(phone));
        phone = "19974787774";
        System.out.println(StringUtil.isPhone(phone));
        phone = "10787787877";
        System.out.println(StringUtil.isPhone(phone));

    }
}
