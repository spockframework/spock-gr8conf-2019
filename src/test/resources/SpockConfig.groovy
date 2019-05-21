randomOrder {
    enabled true
}


/* Default lookup order:
   1. System Property "spock.configuration"
   2. Classpath: /SpockConfig.groovy
   3. $spockUserHome/SpockConfig.groovy
     a. $spockUserHome = System.getProperty("spock.user.home")
     b. $spockUserHome = System.getenv("SPOCK_USER_HOME")
     c. $spockUserHome = System.getProperty("user.home") + "/.spock"
 */
