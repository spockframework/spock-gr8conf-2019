package org.gr8conf.spock

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FieldInfo

class TemporaryDirectoryExtension extends AbstractAnnotationDrivenExtension<TemporaryDirectory> {

    @Override
    void visitFieldAnnotation(TemporaryDirectory annotation, FieldInfo field) {
        super.visitFieldAnnotation(annotation, field)
    }
}
