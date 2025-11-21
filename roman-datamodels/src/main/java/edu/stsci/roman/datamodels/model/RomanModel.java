package edu.stsci.roman.datamodels.model;

import edu.stsci.roman.datamodels.metadata.RomanMetadata;

/**
 * Interface for all Roman datamodels.
 * @param <META> metadata class
 */
public interface RomanModel<META extends RomanMetadata> extends AutoCloseable {
    META getMeta();

    @Override
    void close();
}
