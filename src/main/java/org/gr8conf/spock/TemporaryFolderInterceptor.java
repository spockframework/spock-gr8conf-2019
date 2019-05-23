package org.gr8conf.spock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.spockframework.runtime.extension.IMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;

class TemporaryFolderInterceptor implements IMethodInterceptor {

    private final FieldInfo field;

    TemporaryFolderInterceptor(FieldInfo field) {
        this.field = field;
    }

    @Override
    public void intercept(IMethodInvocation invocation) throws Throwable {
        try (TempFolder folder = new TempFolder()) {
            field.writeValue(invocation.getInstance(), folder.getFolder());
            invocation.proceed();
        }
    }

    static class TempFolder implements AutoCloseable {

        private File folder;

        TempFolder() throws IOException {
            folder = Files.createTempDirectory("spock-tmp").toFile();
        }

        File getFolder() {
            return folder;
        }

        @Override
        public void close() throws Exception {
            if (folder.exists()) {
                deleteRecursively(folder);
            }
        }

        private void deleteRecursively(File dir) {
            Arrays.stream(dir.listFiles(File::isDirectory)).forEach(this::deleteRecursively);

            Arrays.stream(dir.listFiles(File::isFile)).forEach(File::delete);

            dir.delete();
        }
    }
}
