package edu.stsci.roman.datamodels.model;

import edu.stsci.roman.datamodels.RomanDatamodels;
import edu.stsci.roman.datamodels.metadata.FitsWcs;
import edu.stsci.roman.datamodels.metadata.FitsWcsProjectionType;
import edu.stsci.roman.datamodels.testing.RomanDatamodelsReferenceFileType;
import org.asdfformat.asdf.standard.AsdfStandardType;
import org.asdfformat.asdf.testing.ReferenceFileUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static edu.stsci.roman.datamodels.testing.TestCategories.REFERENCE_TESTS;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@Tag(REFERENCE_TESTS)
public class WfiMosaicReferenceTest {
    @Test
    public void testWfiMosaicReferenceFile() throws IOException {
        final Path path = ReferenceFileUtils.getPath(RomanDatamodelsReferenceFileType.WFI_MOSAIC, AsdfStandardType.VERSION_1_6_0.getVersion());

        try (final RomanModel<?> model = RomanDatamodels.open(path)) {
            assertInstanceOf(MosaicModel.class, model);

            final MosaicModel mosaicModel = (MosaicModel)model;

            assertArrayEquals(new double[][] {{1.0, 2.0}, {3.0, 4.0}}, mosaicModel.getData().toArray(new double[0][0]));

            final FitsWcs fitsWcs = mosaicModel.getMeta().getFitsWcs();
            assertArrayEquals(new double[] {5.36, -72.5}, fitsWcs.getCrval().toArray(new double[0]));
            assertArrayEquals(new double[] {1.0, 1.0}, fitsWcs.getCdelt().toArray(new double[0]));
            assertArrayEquals(new double[] {10.0, 10.0}, fitsWcs.getCrpix().toArray(new double[0]));
            assertArrayEquals(new double[][] {{1.0, 0.0}, {0.0, 1.0}}, fitsWcs.getPc().toArray(new double[0][0]));
            assertEquals(FitsWcsProjectionType.TAN, fitsWcs.getProjection());
        }
    }
}
