package edu.stsci.roman.datamodels.model;

import edu.stsci.roman.datamodels.metadata.RomanImageMetadata;
import org.asdfformat.asdf.ndarray.NdArray;

public interface RomanImageModel<META extends RomanImageMetadata> extends RomanModel<META> {
    NdArray<?> getData();
}
