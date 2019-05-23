package org.gr8conf.spock;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.FieldInfo;

public class TemporaryFolderExtension extends AbstractAnnotationDrivenExtension<TemporaryFolder> {

    @Override
    public void visitFieldAnnotation(TemporaryFolder annotation, FieldInfo field) {
        field.getParent().getAllFeatures().forEach(featureInfo ->
                featureInfo.addIterationInterceptor(new TemporaryFolderInterceptor(field)));
    }
}
