package sub;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class Base64ToImage {

	public String change (String text, String fileName) throws IOException{


		byte[] decode = Base64.getDecoder().decode(text);
		System.out.print(decode);
		ByteArrayInputStream bis = new ByteArrayInputStream(decode);
		BufferedImage image = ImageIO.read(bis);
		bis.close();
		// write the image to a file
		File outputfile = new File("/Applications/Eclipse_4.8.0.app/Contents/workspace/MIRAI/WebContent/img/user_img/"+ fileName +".png");
		ImageIO.write(image, "png", outputfile);

		String str = outputfile.getAbsolutePath();

		return str;

	}
}
