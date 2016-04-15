public interface ImageReader {
    public DecodedImage getDecodedImage();
}

public class GifReader implements ImageReader {
    public GifReader( InputStream in ) {
        // check that it's a gif, throw exception if it's not, then if it is decode it.
    }

    public DecodedImage getDecodedImage() {
       return decodedImage;
    }
}

public class JpegReader implements ImageReader {
    //...
}
