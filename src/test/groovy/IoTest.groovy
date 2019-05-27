import org.gr8conf.spock.TemporaryDirectory

import spock.lang.Specification

class IoTest extends Specification {

    @TemporaryDirectory
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

    def "always failing"() {
        expect: false
    }
}
