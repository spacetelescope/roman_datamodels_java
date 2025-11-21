package edu.stsci.roman.datamodels.metadata;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

/**
 * Standard FITS WCS projection type.
 */
@RequiredArgsConstructor
public enum FitsWcsProjectionType {
    AIR("tag:stsci.edu:asdf/transform/airy-"),
    AIT("tag:stsci.edu:asdf/transform/hammer_aitoff-"),
    ARC("tag:stsci.edu:asdf/transform/zenithal_equidistant-"),
    AZP("tag:stsci.edu:asdf/transform/zenithal_perspective-"),
    BON("tag:stsci.edu:asdf/transform/bonne_equal_area-"),
    CAR("tag:stsci.edu:asdf/transform/plate_carree-"),
    CEA("tag:stsci.edu:asdf/transform/cylindrical_equal_area-"),
    COD("tag:stsci.edu:asdf/transform/conic_equidistant-"),
    COE("tag:stsci.edu:asdf/transform/conic_equal_area-"),
    COO("tag:stsci.edu:asdf/transform/conic_orthomorphic-"),
    COP("tag:stsci.edu:asdf/transform/conic_perspective-"),
    CSC("tag:stsci.edu:asdf/transform/cobe_quad_spherical_cube-"),
    CYP("tag:stsci.edu:asdf/transform/cylindrical_perspective-"),
    HPX("tag:stsci.edu:asdf/transform/healpix-"),
    MER("tag:stsci.edu:asdf/transform/mercator-"),
    MOL("tag:stsci.edu:asdf/transform/molleweide-"),
    PAR("tag:stsci.edu:asdf/transform/parabolic-"),
    PCO("tag:stsci.edu:asdf/transform/polyconic-"),
    QSC("tag:stsci.edu:asdf/transform/quad_spherical_cube-"),
    SFL("tag:stsci.edu:asdf/transform/sanson_flamsteed-"),
    SIN("tag:stsci.edu:asdf/transform/slant_orthographic-"),
    STG("tag:stsci.edu:asdf/transform/stereographic-"),
    SZP("tag:stsci.edu:asdf/transform/slant_zenithal_perspective-"),
    TAN("tag:stsci.edu:asdf/transform/gnomonic-"),
    TSC("tag:stsci.edu:asdf/transform/tangential_spherical_cube-"),
    XPH("tag:stsci.edu:asdf/transform/healpix_polar-"),
    ZEA("tag:stsci.edu:asdf/transform/zenithal_equal_area-"),
    ;

    public static Optional<FitsWcsProjectionType> fromTag(final String tag) {
        return Arrays.stream(FitsWcsProjectionType.values())
                .filter(t -> tag.startsWith(t.tagPrefix))
                .findFirst();
    }

    private final String tagPrefix;
}
