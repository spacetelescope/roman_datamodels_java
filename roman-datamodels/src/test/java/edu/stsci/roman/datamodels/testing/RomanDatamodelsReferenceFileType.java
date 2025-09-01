package edu.stsci.roman.datamodels.testing;

import org.asdfformat.asdf.testing.ReferenceFile;

import java.io.IOException;
import java.io.InputStream;

public enum RomanDatamodelsReferenceFileType implements ReferenceFile {
    WFI_MOSAIC,
    ;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public InputStream openScript() throws IOException {
        return RomanDatamodelsReferenceFileType.class.getResourceAsStream(
                String.format("/reference-file-scripts/%s.py", name().toLowerCase())
        );
    }
}
