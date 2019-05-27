package org.gr8conf.spock

import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class GlobalRandomOrderExtension extends AbstractGlobalExtension {

    RandomOrderConfiguration configuration

    @Override
    void visitSpec(SpecInfo spec) {
        if (!configuration.enabled) {
            return
        }
        println "Using RandomOrder for testing. -D${RandomOrderConfiguration.SEED_KEY}=$configuration.seed"

        Random random = new Random(configuration.seed)

        def allFeatures = new ArrayList<>(spec.allFeatures)
        Collections.shuffle(allFeatures, random)
        allFeatures.eachWithIndex { FeatureInfo entry, int i ->
            entry.executionOrder = i
        }
    }
}
