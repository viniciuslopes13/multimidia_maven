package br.com.multimidia.app;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Filtro {

	/*
	 * Método para aplicação do filtro escala de cinza. Uma imagem colorida terá
	 * apenas pixels brancos, pretos ou cinzas;
	 */
	public static BufferedImage escalaDeCinza(BufferedImage imagem) {

		/* Pega largura e altura da imagem */
		int width = imagem.getWidth();
		int height = imagem.getHeight();

		int media = 0;
		/* Laço que percorre a matriz de pixels da imagem */
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				/* A variável rgb recebe o valor RGB do pixel em questão */
				int rgb = imagem.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				/* A média do valor RGB será o valor do pixel na nova imagem */
				media = (r + g + b) / 3;
				/* Cria instância de cor */
				Color color = new Color(media, media, media);
				/* Seta o pixel com a cor instanciada */
				imagem.setRGB(i, j, color.getRGB());
			}
		}
		return imagem;
	}

	/*
	 * Este filtro faz com que a cor de cada pixel da imagem original se transforme
	 * na cor inversa (ex: pixel branco se transforma em pixel preto). A cor inversa
	 * é o valor da subtração entre 255 e o valor RGB.
	 */

	public static BufferedImage negativo(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = 255 - (int) ((rgb & 0x00FF0000) >>> 16);
				int g = 255 - (int) ((rgb & 0x0000FF00) >>> 8);
				int b = 255 - (int) (rgb & 0x000000FF);
				Color color = new Color(r, g, b);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	/*
	 * Este filtro consiste em deixar uma imagem em preto e branco, cada pixel da
	 * imagem original se transforma em um pixel totalmente branco (RGB #FFFFFF) ou
	 * totalmente preto (RGB #000000). Para determinar se um pixel da imagem
	 * original se tornará preto ou branco é usado um limiar, um valor que determina
	 * a partir de qual ponto um pixel se tornará preto ou se tornará branco.
	 */
	public static BufferedImage threshold(BufferedImage image, int limiar) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16);
				int g = (int) ((rgb & 0x0000FF00) >>> 8);
				int b = (int) (rgb & 0x000000FF);
				int media = (r + g + b) / 3;
				Color white = new Color(255, 255, 255);
				Color black = new Color(0, 0, 0);
				if (media < limiar)
					image.setRGB(i, j, black.getRGB());
				else
					image.setRGB(i, j, white.getRGB());
			}
		}
		return image;
	}

}
