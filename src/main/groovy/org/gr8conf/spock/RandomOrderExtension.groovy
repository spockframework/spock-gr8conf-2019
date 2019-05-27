package org.gr8conf.spock

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class RandomOrderExtension extends AbstractAnnotationDrivenExtension<RandomOrder> {

    public static final long seed = System.currentTimeMillis()

    @Override
    void visitSpecAnnotation(RandomOrder annotation, SpecInfo spec) {
        // ignore
    }

    @Override
    void visitSpec(SpecInfo spec) {
        Random random = new Random(seed)
        def allFeatures = new ArrayList<>(spec.allFeatures)
        Collections.shuffle(allFeatures, random)
        allFeatures.eachWithIndex { FeatureInfo entry, int i ->
            entry.executionOrder = i
        }
    }
}
