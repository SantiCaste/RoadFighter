package utils;

import javafx.scene.media.AudioClip;

public final class AudioResources {

	private static AudioClip create(String name) {

		return new AudioClip(ClassLoader.getSystemResource(name).toString());

	}

	public static AudioClip getExplosionAudio() {
		return create("sfx/Explosion2.wav");
//		return create("sfx/hit.wav");
	}

	public static AudioClip getChoqueAudio() {
//		return create("sfx/Choque.wav");
		return create("sfx/wing.wav");
//		return create("sfx/choqueAri.wav");
	}
}