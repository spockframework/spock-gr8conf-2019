package org.gr8conf.spock

import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class GlobalRandomOrderExtension extends AbstractGlobalExtension {

    static final String PROPERTY_KEY = 'randomOrder.seed'

    static final long SEED = Long.getLong(PROPERTY_KEY, System.currentTimeMillis())

    @Override
    void visitSpec(SpecInfo spec) {
        println "Using RandomOrder for testing. -D$PROPERTY_KEY=$SEED"

        Random random = new Random(SEED)

        def allFeatures = new ArrayList<>(spec.allFeatures)
        Collections.shuffle(allFeatures, random)
        allFeatures.eachWithIndex { FeatureInfo entry, int i ->
            entry.executionOrder = i
        }
    }
}
