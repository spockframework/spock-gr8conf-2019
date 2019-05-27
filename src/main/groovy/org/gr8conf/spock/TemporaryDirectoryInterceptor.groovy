package org.gr8conf.spock

import java.nio.file.Files

import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation
import org.spockframework.runtime.model.FieldInfo

class TemporaryDirectoryInterceptor implements IMethodInterceptor {

    private final FieldInfo fieldInfo

    TemporaryDirectoryInterceptor(FieldInfo fieldInfo) {
        this.fieldInfo = fieldInfo
    }

    @Override
    void intercept(IMethodInvocation iMethodInvocation) throws Throwable {
        new TempFolder().withCloseable {
            fieldInfo.writeValue(iMethodInvocation.instance, it.folder)
            iMethodInvocation.proceed()
        }
    }


    static class TempFolder implements AutoCloseable {

        File folder

        TempFolder() throws IOException {
            folder = Files.createTempDirectory("spock-tmp").toFile()
        }

        @Override
        void close() throws Exception {
            if (folder.exists()) {
                deleteRecursively(folder)
            }
        }

        private void deleteRecursively(File dir) {
            dir.listFiles().findAll { it.directory }.each { File f -> deleteRecursively(f) }
            dir.listFiles().findAll { it.file }.each { File f -> f.delete() }

            dir.delete()
        }
    }
}
