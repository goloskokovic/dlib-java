import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(
    compiler="cpp11",
    link="dlib",
    linkpath="lib/macosx-x86_64",
    include={
        "<dlib/image_processing/frontal_face_detector.h>",
        "<dlib/image_io.h>",
        "<iostream>"
    }
)
@Namespace("dlib")
public class dlib {
    @NoOffset
    public static class frontal_face_detector extends BytePointer {
        static { Loader.load(); }
        public frontal_face_detector() { allocate(); }
        private native void allocate();
    }

    // to call the getter and setter functions 
    public static native @ByVal frontal_face_detector get_frontal_face_detector();

    public static void main(String[] args) {
        // Pointer objects allocated in Java get deallocated once they become unreachable,
        // but C++ destructors can still be called in a timely fashion with Pointer.deallocate()
        System.out.println("Starting dlib test...");
        Loader.load();
        try {
            frontal_face_detector l = get_frontal_face_detector();
        } catch(java.lang.UnsatisfiedLinkError e) {
            System.out.println("Problem occurred...");
            e.printStackTrace();
        }
        System.out.println("Success!");
    }
}