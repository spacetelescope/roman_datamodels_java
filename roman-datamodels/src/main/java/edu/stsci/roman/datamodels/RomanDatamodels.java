package edu.stsci.roman.datamodels;

import edu.stsci.roman.datamodels.exception.RomanDatamodelsException;
import edu.stsci.roman.datamodels.model.MosaicModel;
import edu.stsci.roman.datamodels.model.RomanModel;
import org.asdfformat.asdf.Asdf;
import org.asdfformat.asdf.AsdfFile;
import org.asdfformat.asdf.node.AsdfNode;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class RomanDatamodels {
    private static final Map<String, BiFunction<AsdfFile, AsdfNode, RomanModel<?>>> MODELS = new HashMap<>();
    static {
        MODELS.put(MosaicModel.TAG_PREFIX, MosaicModel::new);
    }

    public static RomanModel<?> open(final Path path) throws IOException {
        final AsdfFile asdfFile = Asdf.open(path);

        if (!asdfFile.getTree().containsKey("roman")) {
            asdfFile.close();
            throw new RomanDatamodelsException("ASDF file does not appear to be a Roman file");
        }

        final AsdfNode romanNode = asdfFile.getTree().get("roman");
        for (final Map.Entry<String, BiFunction<AsdfFile, AsdfNode, RomanModel<?>>> entry : MODELS.entrySet()) {
            if (romanNode.getTag().startsWith(entry.getKey())) {
                try {
                    return entry.getValue().apply(asdfFile, romanNode);
                } catch (final Exception e) {
                    asdfFile.close();
                    throw e;
                }
            }
        }

        asdfFile.close();

        throw new RomanDatamodelsException(String.format(
                "Roman tag '%s' not yet supported by this library",
                romanNode.getTag()
        ));
    }
}
