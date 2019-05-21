package org.gr8conf.spock;

import spock.config.ConfigurationObject;

@ConfigurationObject("randomOrder")
public class RandomOrderConfiguration {
    public boolean enabled = Boolean.getBoolean("randomOrder.enabled");

    public long seed = Long.getLong("randomOrder.seed", System.currentTimeMillis());
}
