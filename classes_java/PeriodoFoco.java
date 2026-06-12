public class PeriodoFoco extends Periodo {
	private Conteudo conteudo;

	public PeriodoFoco(int duracaoPrevista, Conteudo _conteudo){
        super(duracaoPrevista);
		conteudo = _conteudo;
    }

	public Conteudo getConteudo() {
		return conteudo;
	}
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
}
