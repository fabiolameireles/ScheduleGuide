public abstract class Periodo {
	private int duracaoPrevista;
	private int duracaoReal;

	public Periodo(int _duracaoPrevista){
		duracaoPrevista = _duracaoPrevista;
		duracaoReal = -1;
	}

	public int getDuracaoPrevista() {
		return duracaoPrevista;
	}
	public void setDuracaoPrevista(int duracaoPrevista) {
		this.duracaoPrevista = duracaoPrevista;
	}

	public int getDuracaoReal() {
		return duracaoReal;
	}
	public void setDuracaoReal(int duracaoReal) {
		this.duracaoReal = duracaoReal;
	}
}
