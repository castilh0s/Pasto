package pasto.entidade;

import java.awt.Point;

import javax.swing.ImageIcon;

import pasto.Pasto;

public class Ovelha extends Animal implements Entidade {
	private final ImageIcon imagem = new ImageIcon("imagens/sheep.gif");
	private boolean vivo = true;
	private static final int REPRODUCAO = 25;
	private static final int MOVIMENTO = 10;
	private static final int COMIDA = 30;

	public Ovelha(Pasto pasto) {
		super(pasto);
		tempoParaNovaReproducao = REPRODUCAO;
		tempoParaMover = MOVIMENTO;
		tempoSemComida = COMIDA;
	}

	public Ovelha(Pasto pasto, boolean vivo) {
		super(pasto);
		this.vivo = vivo;
	}

	@Override
	public void reproduzir() {
		Point vizinho = (Point) getMembroAleatorio(pasto.getVizinhosLivres(this));

		pasto.adicionaEntidade(new Ovelha(pasto), vizinho);

	}

	@Override
	public void tick() {
		if (vivo) {
			tempoParaNovaReproducao--;
			tempoParaMover--;
			tempoSemComida--;
			if (tempoParaNovaReproducao == 0) {
				reproduzir();

				tempoParaNovaReproducao = REPRODUCAO;
			}
			if (tempoParaMover == 0) {
				Point neighbour = (Point) getMembroAleatorio(pasto.getVizinhosLivres(this));

				if (neighbour != null)
					pasto.moveEntidade(this, neighbour);

				tempoParaMover = MOVIMENTO;
			}
			if (tempoSemComida == 0) {
				vivo = false;
			}
		}
	}

	@Override
	public ImageIcon getImagem() {
		return imagem;
	}

	@Override
	public boolean eCompativel(Entidade outraEntidade) {
		System.out.println(!(outraEntidade instanceof Cerca || outraEntidade instanceof Ovelha));
		return !(outraEntidade instanceof Cerca || outraEntidade instanceof Ovelha);
	}
}