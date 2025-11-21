package edu.stsci.roman.datamodels.metadata;

import edu.stsci.roman.datamodels.exception.RomanDatamodelsException;
import lombok.RequiredArgsConstructor;
import org.asdfformat.asdf.ndarray.DoubleNdArray;
import org.asdfformat.asdf.node.AsdfNode;

/**
 * Wrapper for a Roman GWCS object that exposes FITS WCS parameters.
 */
@RequiredArgsConstructor
public class FitsWcs {
    private static final String TRANSFORM_TAG_PREFIX = "tag:stsci.edu:gwcs/fitswcs_imaging-";

    public static FitsWcs fromWcsNode(final AsdfNode wcsNode) {
        for (final AsdfNode stepNode : wcsNode.get("steps")) {
            final AsdfNode transformNode = stepNode.get("transform");
            if (transformNode.getTag().startsWith(TRANSFORM_TAG_PREFIX)) {
                return new FitsWcs(transformNode);
            }
        }

        throw new RomanDatamodelsException("WCS does not contain a FITS-compatible transform");
    }

    private final AsdfNode transformNode;

    /**
     * Pixel coordinate of the reference point, 0-indexed.
     */
    public DoubleNdArray getCrpix() {
        return transformNode.getNdArray("crpix").asDoubleNdArray();
    }

    /**
     * Celestial longitude and latitude of the fiducial point (in degrees).
     */
    public DoubleNdArray getCrval() {
        return transformNode.getNdArray("crval").asDoubleNdArray();
    }

    /**
     * Coordinate scales.
     */
    public DoubleNdArray getCdelt() {
        return transformNode.getNdArray("cdelt").asDoubleNdArray();
    }

    /**
     * Linear transformation matrix.
     */
    public DoubleNdArray getPc() {
        return transformNode.getNdArray("pc").asDoubleNdArray();
    }

    /**
     * Celestial sphere projection.
     */
    public FitsWcsProjectionType getProjection() {
        return FitsWcsProjectionType.fromTag(transformNode.get("projection").getTag())
                .orElseThrow(() -> new RomanDatamodelsException("Unrecognized projection"));
    }
}