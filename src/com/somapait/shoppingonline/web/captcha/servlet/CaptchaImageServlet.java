package com.somapait.shoppingonline.web.captcha.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.log.LogUtil;
import util.string.RandomUtil;
import util.web.SessionUtil;

public class CaptchaImageServlet extends HttpServlet {

	private static final long serialVersionUID = 4868588759771493151L;

	public static final String DEFAULT_SESSION_ATTRIBUTE = "captcha";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaImageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImage(request, response);
	}

	public String randomStr() {
		String strCaptCha = "";
		try {
			strCaptCha = RandomUtil.getRandomString(6, true, true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strCaptCha;
	}

	public void getImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");
		StringBuffer finalString = null;

		String strCaptcha = randomStr();
		if(SessionUtil.requestParameter("captchakey") == null){
			SessionUtil.setAttribute(DEFAULT_SESSION_ATTRIBUTE, strCaptcha);
		}else {
			SessionUtil.setAttribute(SessionUtil.requestParameter("captchakey"), strCaptcha);
		}
		try {
			// Color backgroundColor = Color.lightGray;
			// Color borderColor = Color.lightGray;
			Color textColor = Color.white;
			// Color circleColor = new Color(160, 160, 160);
			Color circleColor = Color.white;
			Font textFont = new Font("Tahoma", Font.PLAIN, 48);
			int charsToPrint = 6;
			int width = request.getParameter("width") != null ? Integer.parseInt(request.getParameter("width")) : 300;
			int height = request.getParameter("height") != null ? Integer.parseInt(request.getParameter("height")) : 160;
			int circlesToDraw = 6;
			float horizMargin = 20.0f;
			// float imageQuality = 0.95f;
			double rotationRange = 0.7;
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			Graphics g = bufferedImage.getGraphics();

//			g.setColor(Color.decode("0x6FB1E8"));
			g.setColor(Color.decode("0xbf37b8"));
			g.fillRect(0, 0, width, height);

			g.setColor(circleColor);

			for (int i = 0; i < circlesToDraw; i++) {
				int circleRadius = (int) (Math.random() * height / 2.0);
				int circleX = (int) (Math.random() * width - circleRadius);
				int circleY = (int) (Math.random() * height - circleRadius);
				g.drawOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
			}

			g.setColor(textColor);
			g.setFont(textFont);

			FontMetrics fontMetrics = g.getFontMetrics();
			int maxAdvance = fontMetrics.getMaxAdvance();
			int fontHeight = fontMetrics.getHeight();

			char[] chars = strCaptcha.toCharArray(); // ***

			float spaceForLetters = -horizMargin * 2 + width;
			float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);

			// AffineTransform transform = ((Graphics2D) g).getTransform();

			finalString = new StringBuffer();

			for (int i = 0; i < charsToPrint; i++) {
				char characterToShow = chars[i];
				finalString.append(characterToShow);

				// int charImageWidth = maxAdvance * 2;
				// int charImageHeight = fontHeight * 2;
				int charWidth = fontMetrics.charWidth(characterToShow);
				int charDim = Math.max(maxAdvance, fontHeight);
				int halfCharDim = (int) (charDim / 2);

				BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
				Graphics2D charGraphics = charImage.createGraphics();
				charGraphics.translate(halfCharDim, halfCharDim);
				double angle = (Math.random() - 0.5) * rotationRange;
				charGraphics.transform(AffineTransform.getRotateInstance(angle));
				charGraphics.translate(-halfCharDim, -halfCharDim);
				charGraphics.setColor(textColor);
				charGraphics.setFont(textFont);

				int charX = (int) (0.5 * charDim - 0.5 * charWidth);
				charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));

				float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
				int y = (int) ((height - charDim) / 2);
				g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);

				charGraphics.dispose();
			}

			g.dispose();

			ImageIO.write(bufferedImage, "PNG", response.getOutputStream());
		} catch (Exception e) {
			LogUtil.COMMON.error("", e);
			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
			return;
		}
	}
}
