package org.github.challengezhou.test;

import org.github.challengezhou.annotation.Builder;
import org.github.challengezhou.annotation.BuilderField;

@Builder("abc")
public class TestBuilder {

    @BuilderField("field")
    private String fieldTest;

    public String getFieldTest() {
        return fieldTest;
    }

    public void setFieldTest(String fieldTest) {
        this.fieldTest = fieldTest;
    }
}
