package org.gr8conf.spock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.SpecInfo;

public class RandomOrderExtension extends AbstractAnnotationDrivenExtension<RandomOrder> {

    public static final String RANDOM_ORDER_EXTENSION_SEED = "org.gr8conf.spock.RandomOrderExtension.seed";

    private static final long SEED = Long.getLong(RANDOM_ORDER_EXTENSION_SEED, System.currentTimeMillis());

    @Override
    public void visitSpecAnnotation(RandomOrder annotation, SpecInfo spec) {
        // ignore
    }

    @Override
    public void visitSpec(SpecInfo spec) {
        System.out.println(String.format("Randomizing execution order use -D%s=%d to recreate.",RANDOM_ORDER_EXTENSION_SEED, SEED));
        List<FeatureInfo> allFeatures = new ArrayList<>(spec.getBottomSpec().getAllFeatures());
        Collections.shuffle(allFeatures, new Random(SEED));
        for (int i = 0; i < allFeatures.size(); i++) {
            allFeatures.get(i).setExecutionOrder(i);
        }
    }
}
