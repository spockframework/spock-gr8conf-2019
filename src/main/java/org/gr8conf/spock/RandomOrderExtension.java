package org.gr8conf.spock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.SpecInfo;

public class RandomOrderExtension extends AbstractAnnotationDrivenExtension<RandomOrder> {
    @Override
    public void visitSpecAnnotation(RandomOrder annotation, SpecInfo spec) {
        // ignore
    }

    @Override
    public void visitSpec(SpecInfo spec) {
        List<FeatureInfo> allFeatures = new ArrayList<>(spec.getBottomSpec().getAllFeatures());
        Collections.shuffle(allFeatures);
        for (int i = 0; i < allFeatures.size(); i++) {
            allFeatures.get(i).setExecutionOrder(i);
        }
    }
}
