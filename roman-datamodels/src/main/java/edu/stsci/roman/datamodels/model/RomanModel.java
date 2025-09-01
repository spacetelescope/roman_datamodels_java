package edu.stsci.roman.datamodels.model;

import edu.stsci.roman.datamodels.metadata.RomanMetadata;

public interface RomanModel<META extends RomanMetadata> extends AutoCloseable {
    META getMeta();

    @Override
    void close();
}
