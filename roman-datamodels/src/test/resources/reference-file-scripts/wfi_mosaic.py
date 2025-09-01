from roman_datamodels import stnode
from gwcs import FITSImagingWCSTransform
from gwcs import coordinate_frames as cf
from gwcs import wcs
from astropy import coordinates as coord
from astropy.modeling.models import Pix2Sky_Gnomonic
from astropy import units as u

roman = stnode.WfiMosaic.create_fake_data(shape=(2, 2))

roman.data = np.array([[1.0, 2.0], [3.0, 4.0]], dtype=np.float32)

tan = Pix2Sky_Gnomonic()
fwcs = FITSImagingWCSTransform(tan, crpix=[10,10], crval=[5.36, -72.5])
detector_frame = cf.Frame2D(name="detector", axes_names=("x", "y"), unit=(u.pix, u.pix))
sky_frame = cf.CelestialFrame(reference_frame=coord.ICRS(), name="icrs",unit=(u.deg, u.deg))
wcsobj = wcs.WCS([(detector_frame, fwcs), (sky_frame, None)])
roman.meta.wcs = wcsobj

af["roman"] = roman
