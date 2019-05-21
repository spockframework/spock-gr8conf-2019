package org.gr8conf.spock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.spockframework.runtime.extension.AbstractGlobalExtension;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.SpecInfo;

public class GlobalRandomExecutionOrderExtension extends AbstractGlobalExtension {

    private RandomOrderConfiguration configuration;

    @Override
    public void visitSpec(SpecInfo spec) {
        if(configuration.enabled) {
            System.out.println(String.format("Randomizing execution order use -D%s=%d to recreate.", "randomOrder.seed", configuration.seed));
            List<FeatureInfo> allFeatures = new ArrayList<>(spec.getBottomSpec().getAllFeatures());
            Collections.shuffle(allFeatures, new Random(configuration.seed));
            for (int i = 0; i < allFeatures.size(); i++) {
                allFeatures.get(i).setExecutionOrder(i);
            }
        }

    }
}
