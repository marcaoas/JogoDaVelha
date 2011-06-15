package br.com.jugms.jogodavelha;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class JogoDaVelhaActivity extends Activity {
    /** Called when the activity is first created. */
    
	private TextView jogador;
	private Button btnNovoJogo;
	private Button btnRetry;
	private Button btnRetry2;
	private List<Botao> botoes;
	private int[] pontos;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTheGame();
    }
	
	private void startTheGame(){
		setContentView(R.layout.main);
		inicializarVariaveis();
		setBotoesComoVazio();
		jogador.setText("Jogador 1");
		setTodosBotoesClickable(true);
        btnNovoJogo = (Button) findViewById(R.id.novo_jogo);
        btnNovoJogo.setOnClickListener(novoJogo());
	}
	
	private void inicializarVariaveis(){
		botoes = new ArrayList<Botao>();
		
		Botao btn;
		btn = new Botao((ImageButton) findViewById(R.id.btn_1),1);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_2),2);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_3),3);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_4),4);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_5),5);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_6),6);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_7),7);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_8),8);
        botoes.add(btn);
        btn = new Botao((ImageButton) findViewById(R.id.btn_9),9);
        botoes.add(btn);
        
        for(Botao botao:botoes){
        	ImageButton imageButton = botao.getImageButton();
        	imageButton.setOnClickListener(click(botao));
        }
        
        pontos = zerarTudo();
		jogador = (TextView) findViewById(R.id.jogador);
	}

	private OnClickListener retry() {
		return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startTheGame();
			}
		};
	}

	private int[] zerarTudo() {
		int[] pontos = {0,0,0,0,0,0,0,0,0};
		return pontos;
	}

	private OnClickListener click(final Botao btn) {
		return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				marcarBotao(btn.getImageButton());
				marcarPontoJogador(btn);
				if(verificaVencedor())
					setVencedor();
				else if (deuVelha()){
					empate();
				}
				trocaJogador();
				btn.getImageButton().setClickable(false);
			}

		};
	}
	
	private void marcarBotao(ImageButton btn){
		if(isJogador1())
			setImagemXNoBotao(btn);
		else
			setImagemCirculoNoBotao(btn);
	}
	
	private void empate() {
		setContentView(R.layout.empate);
		btnRetry2 = (Button) findViewById(R.id.retry_2);
		btnRetry2.setOnClickListener(retry());
	}

	
	private boolean deuVelha() {
		boolean ultimaJogada = true;
		
		for(int i=0;i<9;i++){
			if(pontos[i]==0)
				ultimaJogada = false;
		}
		
		if(ultimaJogada && !verificaVencedor())
			return true;
		else 
			return false;
	}
	
	private OnClickListener novoJogo(){
		return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setBotoesComoVazio();
				jogador.setText("Jogador 1");
				setTodosBotoesClickable(true);
				pontos = zerarTudo();
			}
		};
	}
	
	private void marcarPontoJogador(Botao btn){
		
		int var;
		if(isJogador1())
			var = 1;
		else
			var = 2;
		
		pontos[btn.getPosicao()] = var;
	}
	
	private void setBotoesComoVazio() {
		for(Botao btn: botoes){
			setImagemBrancoNoBotao(btn.getImageButton());
		}
	}
	
	private void setTodosBotoesClickable(boolean valor){
		for(Botao btn: botoes){
			btn.getImageButton().setClickable(valor);
		}	
	}
	
	private boolean verificaVencedor(){
		int n;
		
		if(isJogador1())
			n=1;
		else
			n=2;
		
		//verifica todos as possiveis combinações
		if(pontos[0]==n && pontos[1]==n && pontos[2]==n)
			return true;
		if(pontos[0]==n && pontos[4]==n && pontos[8]==n)
			return true;
		if(pontos[0]==n && pontos[3]==n && pontos[6]==n)
			return true;
		if(pontos[1]==n && pontos[4]==n && pontos[7]==n)
			return true;
		if(pontos[2]==n && pontos[5]==n && pontos[8]==n)
			return true;
		if(pontos[2]==n && pontos[4]==n && pontos[6]==n)
			return true;
		if(pontos[3]==n && pontos[4]==n && pontos[5]==n)
			return true;
		if(pontos[6]==n && pontos[7]==n && pontos[8]==n)
			return true;

		return false;
	}
	
	private boolean isJogador1() {
		String txt = (String) jogador.getText();
		if(txt.equals("Jogador 1"))
			return true;
		else
			return false;
	}
	
	private void trocaJogador(){
		if(isJogador1())
			jogador.setText("Jogador 2");
		else
			jogador.setText("Jogador 1");
	}
	
	private void setVencedor(){
		setContentView(R.layout.game_over);
		TextView jogadorNome = (TextView) findViewById(R.id.vencedor);
		if(isJogador1())
			jogadorNome.setText("Jogador 1 venceu");
		else
			jogadorNome.setText("Jogador 2 venceu");
		btnRetry = (Button) findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(retry());
	}
	
	private void setImagemXNoBotao(ImageButton btn){
		btn.setBackgroundResource(R.drawable.x);
	}
	
	private void setImagemCirculoNoBotao(ImageButton btn){
		btn.setBackgroundResource(R.drawable.circulo);
	}
	private void setImagemBrancoNoBotao(ImageButton btn){
		btn.setBackgroundResource(R.drawable.branco);
	}

}