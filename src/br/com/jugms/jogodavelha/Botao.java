package br.com.jugms.jogodavelha;

import android.widget.ImageButton;

public class Botao {
	
	private int posicao;
	private ImageButton imageButton;
	
	public Botao(ImageButton botao, int posicao){
		this.imageButton = botao;
		this.posicao = posicao-1;
	}
	
	public ImageButton getImageButton() {
		return imageButton;
	}
	public int getPosicao() {
		return posicao;
	}

}
