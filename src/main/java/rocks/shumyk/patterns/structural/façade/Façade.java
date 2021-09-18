package rocks.shumyk.patterns.structural.façade;

import java.util.ArrayList;
import java.util.List;

class Buffer {
	private final char[] characters;
	private final int lineWidth;

	public Buffer(int lineHeight, int lineWidth) {
		this.lineWidth = lineWidth;
		characters = new char[lineWidth * lineHeight];
	}

	public char charAt(int x, int y) {
		return characters[y * lineWidth + x];
	}
}

class Viewport {

	private final Buffer buffer;
	private final int width;
	private final int height;
	private final int offsetX;
	private final int offsetY;

	public Viewport(Buffer buffer, int width, int height, int offsetX, int offsetY) {
		this.buffer = buffer;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public char charAt(int x, int y) {
		return buffer.charAt(x + offsetX, y + offsetY);
	}
}

class Console {
	private final List<Viewport> viewports = new ArrayList<>();
	int width, height;

	public Console(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void addViewport(Viewport viewport) {
		viewports.add(viewport);
	}

	/*
	The all sense of facade is in this method.
	As you can see we have pretty complex system that could be adjusted many ways user wants it, but usually it would be
	efficient just to have common default configuration of this system to have it just to work.

	This method provides such solution - default configuration with complex creation logic hidden after handy facade method Console.newConsole(..)
	 */
	public static Console newConsole(int width, int height) {
		final Buffer buffer = new Buffer(height, width);
		final Viewport viewport = new Viewport(buffer, width, height, 0, 0);
		final Console console = new Console(width, height);
		console.addViewport(viewport);
		return console;
	}

	public void render() {
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				for (Viewport vp : viewports) {
					System.out.print(vp.charAt(x, y));
				}
			}
			System.out.println();
		}
	}
}

public class Façade {
	public static void main(String[] args) {
		final Console console = Console.newConsole(30, 20);
		console.render();
	}
}
