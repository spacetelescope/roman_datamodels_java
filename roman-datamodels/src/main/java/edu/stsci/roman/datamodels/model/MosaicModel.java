package edu.stsci.roman.datamodels.model;

import edu.stsci.roman.datamodels.exception.RomanDatamodelsException;
import edu.stsci.roman.datamodels.metadata.FitsWcs;
import edu.stsci.roman.datamodels.metadata.RomanImageMetadata;
import lombok.RequiredArgsConstructor;
import org.asdfformat.asdf.AsdfFile;
import org.asdfformat.asdf.ndarray.DoubleNdArray;
import org.asdfformat.asdf.node.AsdfNode;

import java.io.IOException;

@RequiredArgsConstructor
public class MosaicModel implements RomanImageModel<MosaicModel.Meta> {
    public static final String TAG_PREFIX = "asdf://stsci.edu/datamodels/roman/tags/wfi_mosaic-";

    private final AsdfFile asdfFile;
    private final AsdfNode node;

    /**
     * Metadata.
     */
    @Override
    public Meta getMeta() {
        return new Meta(node.get("meta"));
    }

    /**
     * The science data array, excluding the border reference pixels in units of
     * MJy / steradian.
     */
    @Override
    public DoubleNdArray getData() {
        return node.getNdArray("data").asDoubleNdArray();
    }

    @Override
    public void close() {
        try {
            asdfFile.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequiredArgsConstructor
    public static class Meta implements RomanImageMetadata {
        private final AsdfNode node;

        /**
         * Fetch the WCS as a FitsWcs object.
         * @throws RomanDatamodelsException if the WCS does not contain a FITS-compatible transform.
         */
        @Override
        public FitsWcs getFitsWcs() {
            return FitsWcs.fromWcsNode(node.get("wcs"));
        }
    }
}
