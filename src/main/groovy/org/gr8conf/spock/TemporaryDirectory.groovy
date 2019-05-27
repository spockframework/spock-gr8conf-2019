package org.gr8conf.spock

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

import org.spockframework.runtime.extension.ExtensionAnnotation

@ExtensionAnnotation(TemporaryDirectoryExtension)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface TemporaryDirectory {

}
