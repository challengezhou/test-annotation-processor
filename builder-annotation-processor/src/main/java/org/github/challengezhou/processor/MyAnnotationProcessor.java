package org.github.challengezhou.processor;

import org.github.challengezhou.ValidCheckUtil;
import org.github.challengezhou.annotation.Builder;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.*;

public class MyAnnotationProcessor extends AbstractProcessor {

    private Elements mElementUtils;
    private Types mTypeUtils;
    private Filer mFiler;
    private CodeGenerator mCodeGenerator;

    @Override
    public synchronized void init(ProcessingEnvironment processingEvn) {
        super.init(processingEvn);
        mElementUtils = processingEvn.getElementUtils();
        mTypeUtils = processingEnv.getTypeUtils();
        mFiler = processingEvn.getFiler();
        mCodeGenerator = new CodeGenerator(mElementUtils, mFiler);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annoTypes = new HashSet<>();
        annoTypes.add(Builder.class.getCanonicalName());
        return annoTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        try {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Builder
                    .class);
            for (Element element : elements) {
                if (!ValidCheckUtil.isValidClass(element)) {
                    continue;
                }

                List<? extends Element> memberFields = mElementUtils.getAllMembers((TypeElement)
                        element);
                List<VariableElement> annoFields = new ArrayList<>();

                if (memberFields == null) {
                    return false;
                }

                for (Element member : memberFields) {
                    if (ValidCheckUtil.isValidField(member)) {
                        annoFields.add((VariableElement) member);
                    }
                }

                mCodeGenerator.generatorCode((TypeElement) element, annoFields);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

}