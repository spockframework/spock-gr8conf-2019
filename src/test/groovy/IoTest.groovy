import java.nio.file.Files

import spock.lang.AutoCleanup
import spock.lang.Specification

class IoTest extends Specification {

    @AutoCleanup('delete')
    File parent = Files.createTempDirectory("spock-tmp").toFile()

    @AutoCleanup('delete')
    File target  = new File(parent, 'test.txt')

    def setup() {
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
