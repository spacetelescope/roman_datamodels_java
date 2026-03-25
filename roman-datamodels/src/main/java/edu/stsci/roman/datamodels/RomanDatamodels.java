package edu.stsci.roman.datamodels;

import edu.stsci.roman.datamodels.exception.RomanDatamodelsException;
import edu.stsci.roman.datamodels.model.MosaicModel;
import edu.stsci.roman.datamodels.model.RomanModel;
import org.apache.commons.lang3.function.TriFunction;
import org.asdfformat.asdf.Asdf;
import org.asdfformat.asdf.AsdfFile;
import org.asdfformat.asdf.node.AsdfNode;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Static methods for reading Roman ASDF files.
 */
public class RomanDatamodels {
    private static final Map<String, TriFunction<AsdfFile, AsdfNode, Boolean, RomanModel<?>>> MODELS = new HashMap<>();
    static {
        MODELS.put(MosaicModel.TAG_PREFIX, MosaicModel::new);
    }

    private RomanDatamodels() {
    }

    /**
     * Open a Roman ASDF file and wrap it in a datamodel class.
     * @param path path to the .asdf file
     * @return datamodel instance (type appropriate to the file)
     * @throws IOException I/O failure
     * @throws RomanDatamodelsException when this library cannot handle the file content
     */
    public static RomanModel<?> open(final Path path) throws IOException {
        final AsdfFile asdfFile = Asdf.open(path);

        try {
            return open(asdfFile, true);
        } catch (final Exception e) {
            asdfFile.close();
            throw e;
        }
    }

    /**
     * Wrap a Roman ASDF file in a datamodel class.
     * @param asdfFile open ASDF file
     * @return datamodel instance (type appropriate to the file)
     * @throws RomanDatamodelsException when this library cannot handle the file content
     */
    public static RomanModel<?> open(final AsdfFile asdfFile) {
        return open(asdfFile, false);
    }

    private static RomanModel<?> open(final AsdfFile asdfFile, final boolean manageAsdfFile) {
        if (!asdfFile.getTree().containsKey("roman")) {
            throw new RomanDatamodelsException("ASDF file does not appear to be a Roman file");
        }

        final AsdfNode romanNode = asdfFile.getTree().get("roman");
        for (final Map.Entry<String, TriFunction<AsdfFile, AsdfNode, Boolean, RomanModel<?>>> entry : MODELS.entrySet()) {
            if (romanNode.getTag().startsWith(entry.getKey())) {
                return entry.getValue().apply(asdfFile, romanNode, manageAsdfFile);
            }
        }

        throw new RomanDatamodelsException(String.format(
                "Roman tag '%s' not yet supported by this library",
                romanNode.getTag()
        ));
    }
}
