package org.gr8conf.spock

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FieldInfo
import org.spockframework.runtime.model.SpecInfo

class TemporaryDirectoryExtension extends AbstractAnnotationDrivenExtension<TemporaryDirectory> {

    @Override
    void visitFieldAnnotation(TemporaryDirectory annotation, FieldInfo field) {
        SpecInfo parent = field.parent
        parent.allFeatures*.addIterationInterceptor(new TemporaryDirectoryInterceptor(field))
    }
}
