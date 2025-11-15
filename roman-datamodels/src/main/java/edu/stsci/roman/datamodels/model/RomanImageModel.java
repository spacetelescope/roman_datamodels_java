package edu.stsci.roman.datamodels.model;

import edu.stsci.roman.datamodels.metadata.RomanImageMetadata;
import org.asdfformat.asdf.ndarray.NdArray;

/**
 * Interface for Roman datamodels that provide image data.
 * @param <META> metadata class
 */
public interface RomanImageModel<META extends RomanImageMetadata> extends RomanModel<META> {
    NdArray<?> getData();
}
