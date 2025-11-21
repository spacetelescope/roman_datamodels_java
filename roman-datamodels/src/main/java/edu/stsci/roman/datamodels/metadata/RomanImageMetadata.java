package edu.stsci.roman.datamodels.metadata;

/**
 * Interface for Roman image metadata that exposes the image's FITS WCS parameters.
 */
public interface RomanImageMetadata extends RomanMetadata {
    FitsWcs getFitsWcs();
}
