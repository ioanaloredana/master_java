package ro.tirzuman.ioana.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaController extends HttpServlet {

	private static final long serialVersionUID = 7524137982779010513L;

	//private static String charPool = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
	private static String charPool = "ab";
	private static int charPoolLength = charPool.length();
	private static Random rnd = new Random();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			addCaptcha(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedImage bufferedImage = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();
		//String captcha = getRandomString(1+rnd.nextInt(5));
		String captcha = getRandomString(3);
		request.getSession(true).setAttribute("captcha", captcha);

		int x = 0;
		int y = 0;
		for (int i = 0; i < captcha.length(); i++) {
			x = x + 20 + rnd.nextInt(10);
			y = 20 + rnd.nextInt(20);
			char[] character = new char[1];
			character[0] = captcha.charAt(i);
			g2d.drawChars(character, 0, 1, x, y);
		}

		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	}

	private static String getRandomString(int length) {
		StringBuffer randomString = new StringBuffer("");
		for (int i = 0; i < length; i++) {
			char c = charPool.charAt(rnd.nextInt(charPoolLength));
			if (rnd.nextBoolean()) {
				c = ("" + c).toUpperCase().charAt(0);
			}
			randomString.append(c);
		}

		return randomString.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
