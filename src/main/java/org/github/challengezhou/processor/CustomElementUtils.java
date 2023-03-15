package org.github.challengezhou.processor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

public class CustomElementUtils {

    public static String getPackageName(Elements elements, TypeElement typeElement) {
        return elements.getPackageOf(typeElement).getQualifiedName().toString();
    }

    public static String getSimpleName(TypeElement typeElement) {
        return typeElement.getSimpleName().toString();
    }

    public static String toUpperFirstChar(String charStr) {
        char[] charArray = charStr.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

}
