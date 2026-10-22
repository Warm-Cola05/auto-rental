package com.coder.rental.vo;

import cn.hutool.extra.pinyin.PinyinUtil;

/**
 * 将文字首字母转换为拼音大写
 */
public class PinYinUtils {
    public static String getPinYin(String str) {
        return PinyinUtil.getFirstLetter(str, "").toUpperCase();
    }

}
