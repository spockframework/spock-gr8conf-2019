package org.gr8conf.spock

import spock.config.ConfigurationObject

@ConfigurationObject('randomOrder')
class RandomOrderConfiguration {
    static final String ENABLED_KEY = 'randomOrder.enabled'

    static final String SEED_KEY = 'randomOrder.seed'

    boolean enabled = Boolean.getBoolean(ENABLED_KEY)
    long seed = Long.getLong(SEED_KEY, System.currentTimeMillis())
}
