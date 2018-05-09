package org.apache.avro.mojo;

import main.KotlinGenerator;
import main.SchemaUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Generate Kotlin classes and interfaces from AvroIDL files (.avdl)
 *
 * @goal kotlin-idl-protocol
 * @requiresDependencyResolution runtime
 * @phase generate-sources
 * @threadSafe
 */
public class KotlinIDLProtocolMojo extends AbstractAvroMojo {
    /**
     * A set of Ant-like inclusion patterns used to select files from the source
     * directory for processing. By default, the pattern
     * <code>**&#47;*.avdl</code> is used to select IDL files.
     *
     * @parameter
     */
    private String[] includes = new String[]{"**/*.avdl"};

    /**
     * A set of Ant-like inclusion patterns used to select files from the source
     * directory for processing. By default, the pattern
     * <code>**&#47;*.avdl</co  de> is used to select IDL files.
     *
     * @parameter
     */
    private String[] testIncludes = new String[]{"**/*.avdl"};

    @Override
    protected void doCompile(String filename, File sourceDirectory, File outputDirectory) throws IOException {
        File inputFile = getInputFile(filename, sourceDirectory);
        PrintStream outputStream = SchemaUtils.tryOrThrow(() -> getOutputStream(outputDirectory));
        KotlinGenerator.INSTANCE.generateFromFile(inputFile.getAbsolutePath(), outputStream);
    }

    @NotNull
    private static File getInputFile(String filename, File inputDirectory) {
        inputDirectory.mkdirs();
        return new File(inputDirectory.getAbsolutePath() + "/" + filename);
    }

    @NotNull
    private static PrintStream getOutputStream(File outputDirectory) throws Exception {
        outputDirectory.mkdirs();
        return new PrintStream(new File(outputDirectory.getAbsolutePath() + "/GeneratedCode.kt"));
    }

    @Override
    protected String[] getIncludes() {
        return includes;
    }


    @Override
    protected String[] getTestIncludes() {
        return testIncludes;
    }
}