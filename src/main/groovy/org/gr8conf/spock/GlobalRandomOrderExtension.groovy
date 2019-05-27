package org.gr8conf.spock

import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class GlobalRandomOrderExtension extends AbstractGlobalExtension {

    static final String ENABLED_KEY = 'randomOrder.enabled'

    static final String SEED_KEY = 'randomOrder.seed'

    static final boolean ENABLED = Boolean.getBoolean(ENABLED_KEY)
    static final long SEED = Long.getLong(SEED_KEY, System.currentTimeMillis())

    @Override
    void visitSpec(SpecInfo spec) {
        if (!ENABLED) {
            return
        }
        println "Using RandomOrder for testing. -D$SEED_KEY=$SEED"

        Random random = new Random(SEED)

        def allFeatures = new ArrayList<>(spec.allFeatures)
        Collections.shuffle(allFeatures, random)
        allFeatures.eachWithIndex { FeatureInfo entry, int i ->
            entry.executionOrder = i
        }
    }
}
