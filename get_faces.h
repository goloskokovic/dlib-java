#include <dlib/image_processing/frontal_face_detector.h>
#include <dlib/image_processing.h>
#include <dlib/image_io.h>
#include <iostream>

int get_faces(char* image_path)
{
	dlib::frontal_face_detector detector = dlib::get_frontal_face_detector();
		
	dlib::array2d<dlib::rgb_pixel> img;
	dlib::load_image(img, image_path);
	
	// Make the image bigger by a factor of two.  This is useful since
	// the face detector looks for faces that are about 80 by 80 pixels
	// or larger.  Therefore, if you want to find faces that are smaller
	// than that then you need to upsample the image as we do here by
	// calling pyramid_up().  So this will allow it to detect faces that
	// are at least 40 by 40 pixels in size.  We could call pyramid_up()
	// again to find even smaller faces, but note that every time we
	// upsample the image we make the detector run slower since it must
	// process a larger image.
	pyramid_up(img);

	// Now tell the face detector to give us a list of bounding boxes
	// around all the faces it can find in the image.
	std::vector<dlib::rectangle> dets = detector(img);
	int faces = dets.size();
	
    return faces;
}
