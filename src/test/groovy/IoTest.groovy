import org.gr8conf.spock.TemporaryFolder

import spock.lang.Specification

class IoTest extends Specification {

    @TemporaryFolder
    File parent

    File target

    def setup() {
        target = new File(parent, 'test.txt')
        target.text = 'Hello World'
    }

    def "test"() {
        when:
        def result = target.text

        then:
        result == 'Hello World'
    }

}
