package com.freedom.wecore.tools;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 *  @author vurtne on 15-May-18.
 */
public class StringUtil {

    private static final String TAG = "StringUtils";

    public static final String RAMDOM_BASE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static Pattern pattern = Pattern.compile("(\\[[^\\[\\]]*\\])");

    private static Pattern compile = Pattern.compile("-?[0-9]*.?[0-9]*");

    private static  Pattern phonePattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
    /**
     * md5编码
     */
    public static final String ALGORITHM_MD5 = "MD5";

    /**
     * sha1编码
     */
    public static final String ALGORITHM_SHA1 = "SHA1";

    /**
     * 字符串是否为空(空字符串或null)
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /***
     * 将字符串进行Base64编码。编码结果超过76字符会插入换行符，末尾有换行符
     *
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    /***
     * 将字符串进行Base64编码，去掉其中所有的换行符
     *
     * @param str
     * @return
     */
    public static String base64EncodeNoCR(String str) {
        return base64Encode(str).replaceAll("\\n", "");
    }

    /***
     * 将字符串进行Base64解码
     *
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        return new String(Base64.decode(str.getBytes(), Base64.DEFAULT));
    }

    /**
     * 十进制转十六进制对应表
     */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 字节转十六进制
     *
     * @param bytes
     * @return
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * MD5编码，大写，使用默认charset(UTF-8)
     */
    public static String md5(String str) {
        return encode(str.getBytes(), ALGORITHM_MD5);
    }

    /**
     * MD5编码，小写，使用默认charset(UTF-8)
     */
    public static String md5Lcase(String str) {
        String output = encode(str.getBytes(), ALGORITHM_MD5);
        return output == null ? null : output.toLowerCase(Locale.getDefault());
    }

    /**
     * MD5编码，大写，使用特定charset
     *
     * @param str
     * @param charsetName
     * @return
     */
    public static String md5(String str, String charsetName) {
        try {
            return encode(str.getBytes(charsetName), ALGORITHM_MD5);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5编码，小写，使用特定charset
     *
     * @param str
     * @param charsetName
     * @return
     */
    public static String md5Lcase(String str, String charsetName) {
        String output = md5(str, charsetName);
        return output == null ? null : output.toLowerCase(Locale.getDefault());
    }

    /**
     * MD5编码，小写
     *
     * @param bytes
     * @return
     */
    public static String md5Lcase(byte[] bytes) {
        String output = encode(bytes, ALGORITHM_MD5);
        return output == null ? null : output.toLowerCase(Locale.getDefault());
    }

    /**
     * SHA1编码，大写，使用默认charset(UTF-8)
     */
    public static String sha1(String str) {
        return encode(str.getBytes(), ALGORITHM_SHA1);
    }

    /**
     * SHA1编码，小写，使用默认charset(UTF-8)
     */
    public static String sha1Lcase(String str) {
        String output = encode(str.getBytes(), ALGORITHM_SHA1);
        return output == null ? null : output.toLowerCase(Locale.getDefault());
    }

    /**
     * SHA1编码，大写，使用特定charset
     *
     * @param str
     * @param charsetName
     * @return
     */
    public static String sha1(String str, String charsetName) {
        try {
            return encode(str.getBytes(charsetName), ALGORITHM_SHA1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SHA1编码，小写，使用特定charset
     *
     * @param str
     * @param charsetName
     * @return
     */
    public static String sha1Lcase(String str, String charsetName) {
        String output = sha1(str, charsetName);
        return output == null ? null : output.toLowerCase(Locale.getDefault());
    }

    /**
     * SHA1编码，小写
     *
     * @param bytes
     * @return
     */
    public static String sha1Lcase(byte[] bytes) {
        String output = encode(bytes, ALGORITHM_SHA1);
        return output == null ? null : output.toLowerCase(Locale.getDefault());
    }

    /**
     * SHA1和MD5编码，大写
     *
     * @param bytes     输入
     * @param algorithm 编码算法 {@link#ALGORITHM_MD5}, {@link#ALGORITHM_SHA1}
     * @return 输出
     */
    public static String encode(byte[] bytes, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(bytes);
            return toHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把颜色数值转为#RRGGBB形式
     *
     * @param color
     * @return
     */
    public static String getRGBColor(int color) {
        return "#" + Integer.toHexString(color | 0xff000000).substring(2);
    }

    /**
     * 把颜色数值转为#AARRGGBB形式
     *
     * @param color
     * @return
     */
    public static String getARGBColor(int color) {
        return "#" + Long.toHexString(color | 0xffffffff00000000L).substring(8);
    }

    /**
     * 设置同一个textview里边文字的不同颜色
     *
     * @param format 以中括号标明要上色的文字，例如 "[12人]给了[8种]印象"
     * @param colors 例如 "#dd465e"。如果数量少于format里的括号数，不足的会取最后一个
     * @return 返回的Spanned可以直接用于TextView.setText()
     */
    public static Spanned formatColor(String format, String... colors) {
        if (colors == null || colors.length == 0) {
            return Html.fromHtml(format);
        }
        StringBuffer buffer = new StringBuffer(format.length());
        Matcher matcher = pattern.matcher(format);
        int i = 0;
        while (matcher.find()) {
            int c = i < colors.length ? i : colors.length - 1;
            String group = matcher.group();
            group = "<font color=\"" + colors[c] + "\">" + group.substring(1, group.length() - 1) + "</font>";
            matcher.appendReplacement(buffer, group);
            i++;
        }
        matcher.appendTail(buffer);
        return Html.fromHtml(buffer.toString());
    }

    /**
     * 设置同一个textview里边文字的不同颜色
     *
     * @param format 以中括号标明要上色的文字，例如 "[12人]给了[8种]印象"
     * @param colors 颜色值，例如 Color.RED, 0xffdd465e，忽略透明度。如果数量少于format里的括号数，不足的会取最后一个
     * @return 返回的Spanned可以直接用于TextView.setText()
     */
    public static Spanned formatColor(String format, int... colors) {
        if (colors == null || colors.length == 0) {
            return Html.fromHtml(format);
        }
        String[] colors2 = new String[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors2[i] = getRGBColor(colors[i]);
        }
        return formatColor(format, colors2);
    }

    /**
     * 设置同一个textview里边文字的不同颜色
     *
     * @param format   以中括号标明要上色的文字，例如 "[12人]给了[8种]印象"
     * @param context  上下文
     * @param colorIds 颜色资源id，例如 R.color.eelly_red ，忽略透明度。如果数量少于format里的括号数，不足的会取最后一个
     * @return 返回的Spanned可以直接用于TextView.setText()
     */
    public static Spanned formatColorResource(String format, Context context, int... colorIds) {
        if (colorIds == null || colorIds.length == 0) {
            return Html.fromHtml(format);
        }
        String[] colors2 = new String[colorIds.length];
        for (int i = 0; i < colorIds.length; i++) {
            colors2[i] = getRGBColor(context.getResources().getColor(colorIds[i]));
        }
        return formatColor(format, colors2);
    }

    /**
     * 获得带颜色的文字
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder getColoredText(String text, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        builder.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * @see #getColoredText(String, int)
     */
    public static SpannableStringBuilder getColoredText(Context context, String text, int colorId) {
        return getColoredText(text, context.getResources().getColor(colorId));
    }

    /**
     * @see #getColoredText(String, int)
     */
    public static SpannableStringBuilder getColoredText(Context context, int textId, int colorId) {
        return getColoredText(context.getString(textId), context.getResources().getColor(colorId));
    }

    /**
     * 在字符串中查找第一个匹配的字符
     *
     * @param source     源字符串
     * @param target     要查找的目标字符串
     * @param ignoreCase 是否忽略大小写
     * @return 字符串查找结果, 没找到时返回null
     */
    public static StringFind find(String source, String target, boolean ignoreCase) {
        Pattern pattern = ignoreCase ? Pattern.compile(target, Pattern.CASE_INSENSITIVE | Pattern.LITERAL) : Pattern.compile(target, Pattern.LITERAL);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return new StringFind(matcher.start(), matcher.end(), matcher.group());
        }
        return null;
    }

    /**
     * 字符串转换成 ASCII
     *
     * @param str
     * @return
     */
    public static Long str2Ascii(String str) {
        char[] chars = str.toCharArray();
        Long rs = 0L;
        for (int i = 0; i < chars.length; i++) {
            rs += (int) chars[i];
        }

        return rs;
    }


    /**
     * 字符串查找结果对象
     *
     * @author 李欣
     */
    public static class StringFind {

        /**
         * 查找字符串的开始位置
         */
        public int start;

        /**
         * 查找字符串的结束位置+1
         */
        public int end;

        /**
         * 查找字符串在源字符串中的原始值(主要用于字母大小写的情况获取原始值)
         */
        public String original;

        StringFind(int start, int end, String original) {
            this.start = start;
            this.end = end;
            this.original = original;
        }
    }

    /**
     * 计算字符串的字节长度(半角符号计1，全角符号计2)
     *
     * @param string
     * @return
     */
    public static int getByteLength(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            count += Integer.toHexString(string.charAt(i)).length() == 4 ? 2 : 1;
        }
        return count;
    }

    /**
     * 按指定长度，截断字符串，超长会添加指定后缀<br>
     * 半角符号长度为1，全角符号长度为2
     *
     * @param string 字符串
     * @param length 保留字符串长度
     * @param suffix 超长时添加的后缀
     * @return 截断后的字符串
     */
    public static String trimString(String string, int length, String suffix) {
        if (getByteLength(string) <= length) {
            return string;
        }
        StringBuffer sb = new StringBuffer();
        int count = 0;
        if (suffix == null) {
            suffix = "";
        }
        int slength = getByteLength(suffix);
        for (int i = 0; i < string.length(); i++) {
            char temp = string.charAt(i);
            count += Integer.toHexString(temp).length() == 4 ? 2 : 1;
            if (count + slength <= length) {
                sb.append(temp);
            }
            if (count + slength >= length) {
                break;
            }
        }
        sb.append(suffix);
        return sb.toString();
    }

    /**
     * 按指定长度，截断字符串，超长会添加…<br>
     * 半角符号长度为1，全角符号长度为2
     *
     * @param string 字符串
     * @param length 保留字符串长度
     * @return 截断后的字符串
     */
    public static String trimString(String string, int length) {
        return trimString(string, length, "…");
    }

    /**
     * 根据像素宽度截取字符串<br>
     * 这个是为了某些地方不需要省略号结尾，而TextView的宽度不够，这样最后的字会
     *
     * @param str
     * @param pixel
     * @param textSize
     * @return
     */
    public static String trimPixelLength(String str, int pixel, float textSize) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        float width = getTextWidth(str, textSize);
        if (Math.ceil(width) <= pixel) {
            return str.toString();
        } else {
            for (int i = str.length() - 1; i > 0; i--) {
                String temp = str.substring(0, i);
                float w = getTextWidth(temp, textSize);
                if (Math.ceil(w) <= pixel) {
                    return temp;
                }
            }
        }
        return "";
    }

    private static float getTextWidth(String text, float textSize) {
        TextPaint paint = new TextPaint();
        paint.setTextSize(textSize);
        return paint.measureText(text);
    }

    /**
     * 过滤Html内容,将Html格式的内容转为普通文本
     *
     * @param source
     * @return
     */
    public static String htmlToString(String source) {
        return Html.fromHtml(source).toString();
    }

    /**
     * 金钱单位四舍五入
     *
     * @param money 金钱
     * @return 四舍五入之后的金钱
     */
    public static String moneyRound(String money) {
        if (money.length() == 0) {
            money = "0";
        }
        BigDecimal decimal;
        try {
            decimal = new BigDecimal(Double.valueOf(money));
        }catch (Exception e){
            decimal = new BigDecimal(Double.valueOf("0"));
        }
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * javascript escape
     *
     * @param s
     * @return
     */
    public static String escape(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if (isEscapePersistDigit(ch)) {
                sb.append((char) ch);
            } else if (ch <= 0x007F) {
                sb.append('%');
                sb.append(HEX_DIGITS[(ch & 0xf0) >>> 4]);
                sb.append(HEX_DIGITS[ch & 0x0f]);
            } else {
                sb.append('%');
                sb.append('u');
                sb.append(HEX_DIGITS[(ch & 0xf000) >>> 12]);
                sb.append(HEX_DIGITS[(ch & 0x0f00) >>> 8]);
                sb.append(HEX_DIGITS[(ch & 0x00f0) >>> 4]);
                sb.append(HEX_DIGITS[ch & 0x000f]);
            }
        }
        return sb.toString();
    }

    /**
     * javascript unescape
     *
     * @param s
     * @return
     */
    public static String unescape(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if (isEscapePersistDigit(ch)) {
                sb.append((char) ch);
            } else if (ch == '%') {
                boolean flag = false;
                int cint = 0;
                if ('u' == s.charAt(i + 1) || 'U' == s.charAt(i + 1)) {
                    if (i + 5 < len) {
                        char[] chs = new char[4];
                        s.getChars(i + 2, i + 6, chs, 0);
                        if (isHexDigits(chs)) {
                            cint = Integer.parseInt(new String(chs), 16);
                            i += 5;
                            flag = true;
                        }
                    }
                } else {
                    if (i + 2 < len) {
                        char[] chs = new char[2];
                        s.getChars(i + 1, i + 3, chs, 0);
                        if (isHexDigits(chs)) {
                            cint = Integer.parseInt(new String(chs), 16);
                            i += 2;
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    sb.append((char) cint);
                } else {
                    sb.append((char) ch);
                }
            } else {
                sb.append((char) ch);
            }
            i++;
        }
        return sb.toString();
    }

    private static boolean isHexDigit(int ch) {
        return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
    }

    private static boolean isHexDigits(char[] chs) {
        for (int i = 0; i < chs.length; i++) {
            if (!isHexDigit(chs[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEscapePersistDigit(int ch) {
        return isHexDigit(ch) || ch == '*' || ch == '@' || ch == '-' || ch == '_' || ch == '+' || ch == '.' || ch == '/';
    }

    /**
     * 判断字符是否为数字，包括小数
     *
     * @param input
     * @return
     */
    public static boolean isNumeric(String input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        Matcher isNum = compile.matcher(input);
        return !TextUtils.isEmpty(input) && isNum.matches();
    }

    /**
     * 获取随机字符串，字符由数字和大小写字符组成
     *
     * @param length 获取的字符串长度
     * @return 相应长度的随机字符串
     */
    public static String getRandomString(int length) {
        return getRandomString(length, null);
    }

    /**
     * 获取随机字符串，字符由数字和大小写字符组成
     *
     * @param length 获取的字符串长度
     * @param base   组成随机字符串的字符，传null时，默认使用{@value #RAMDOM_BASE}
     * @return 相应长度的随机字符串
     */
    public static String getRandomString(int length, String base) {
        if (TextUtils.isEmpty(base)) {
            base = RAMDOM_BASE;
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 字符串翻转
     *
     * @param str 要翻转的字符串
     * @return
     */
    public static String reverseString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuffer sb = new StringBuffer(str);
        return sb.reverse().toString();
    }

    /**
     * 拼接字符串。
     * implode(", ", "ab", " ", "abs")
     * implode(", ", new String[] { "ab", " ", "abs" })
     *
     * @param separator
     * @param data
     * @return
     */
    public static String implode(String separator, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(separator);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }

    /**
     * @param separator
     * @param list
     * @return
     * @see #implode(String, String...)
     */
    public static String implode(String separator, List<String> list) {
        String[] arr = list.toArray(new String[list.size()]);
        return implode(separator, arr);
    }

    /**
     * @param text
     * @return
     */
    public static int secureInteger(String text) {
        return secureInteger(text, 0);
    }

    public static int secureInteger(String text, int defaultValue) {

        if (TextUtils.isEmpty(text)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
        }
        return defaultValue;
    }

    public static long secureLong(String text) {

        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static double secureDouble(String text) {

        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static float secureFloat(String text) {

        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param text
     * @return
     */
    public static String secureString(String text) {
        return TextUtils.isEmpty(text) ? "" : text;
    }

    /**
     * @param text
     * @param defaultValue
     * @return
     */
    public static String secureString(String text, String defaultValue) {
        return TextUtils.isEmpty(text) ? defaultValue : text;
    }

    /**
     * @param longStr
     * @return
     */
    public static String floatToShort(String longStr) {
        String rs = "";
        float number = secureFloat(longStr);
        if (number < 10000) {
            rs = number + "";
        } else if (number >= 10000 && number < 100000) {
            rs = String.format("%.2f", (float) number / 10000) + "万";
        } else {
            rs = "10万+";
        }

        return rs;
    }

    /**
     * 数字简短显示
     *
     * @param numberStr
     * @return
     */
    public static String numberToShort(String numberStr) {
        String rs = "";
        int number = secureInteger(numberStr);
        if (number == 0) {
            rs = numberStr;
        } else if (number < 10000) {
            rs = number + "";
        } else if (number >= 10000 && number < 100000) {
            rs = String.format("%.2f", (float) number / 10000) + "万";
        } else {
            rs = "10万+";
        }

        return rs;
    }


    /**
     * 手机号验证
     *
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Matcher m = phonePattern.matcher(str);
        boolean b = m.matches();
        return b;
    }

    /**
     * 身份证号验证
     *
     * @param IDStr
     * @return
     */
    public static boolean isIdentityCard(String IDStr) {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        // String[] Checker = {"1","9","8","7","6","5","4","3","2","1","1"};
        String Ai = "";

        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "号码长度应该为15位或18位。";
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份

        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "生日无效。";
            System.out.println(errorInfo);
            return false;
        }

        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "生日不在有效范围。";
                System.out.println(errorInfo);
                return false;
            }
        } catch (NumberFormatException e) {
            LogUtil.e(TAG, e.toString());
        } catch (ParseException e) {
            LogUtil.e(TAG, e.toString());
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "月份无效";
            System.out.println(errorInfo);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "日期无效";
            System.out.println(errorInfo);
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "地区编码错误。";
            System.out.println(errorInfo);
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，最后一位字母错误";
                System.out.println(errorInfo);
                return false;
            }
        } else {
            System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
            System.out.println("新身份证号:" + Ai);
            return true;
        }
        // =====================(end)=====================
        System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
        return true;

    }

    private static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
                        + "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                        + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))"
                        + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|"
                        + "(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|"
                        + "(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);

        return m.matches();

    }


    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

}
