package com.freedom.wecore.tools;


import com.freedom.wecore.R;

/**
 * @author vurtne on 27-May-18.
 */
public class LanguageUtil {

    public static int switchLanguageColor(String language){
        int colorId = 0;
        switch (language){
            case "Java":
                colorId = R.color.color_AF7126;
                break;
            case "Python":
                colorId = R.color.color_3873A3;
                break;
            case "Kotlin":
                colorId = R.color.color_EF8D3F;
                break;
            case "C++":
                colorId = R.color.color_F14E92;
                break;
            case "HTML":
                colorId = R.color.color_E14D30;
                break;
            case "CSS":
                colorId = R.color.color_553F7A;
                break;
            case "Shell":
                colorId = R.color.color_8BDE5A;
                break;
            case "JavaScript":
                colorId = R.color.color_F0DF64;
                break;
            case "Go":
                colorId = R.color.color_3960A9;
                break;
            case "Groovy":
                colorId = R.color.color_E59E5C;
                break;
            case "C#":
                colorId = R.color.color_1F8413;
                break;
            case "Swift ":
                colorId = R.color.color_FDAB50;
                break;
            case "C":
                colorId = R.color.color_555555;
                break;
            case "PHP":
                colorId = R.color.color_4F5E93;
                break;
            case "Ruby":
                colorId = R.color.color_6E1619;
                break;
            case "Objective-C":
                colorId = R.color.color_4791FC;
                break;
            case "Objective-C++":
                colorId = R.color.color_696BF7;
                break;
            case "Vue":
                colorId = R.color.color_2D3E4F;
                break;
            case "Jupyter Notebook":
                colorId = R.color.color_D85B1F;
                break;
            case "Visual Basic":
                colorId = R.color.color_935FB5;
                break;
            default:break;
        }
        return colorId;
    }
}
