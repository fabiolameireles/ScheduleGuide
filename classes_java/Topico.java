import java.util.List;

public class Topico {
	private String nome;
	private String imagem;
	private Prioridade prioridade;
	private boolean ativo;
	private List<Conteudo> conteudos;
	private Categoria categoria;

	public Topico(String _nome, String _imagem, Prioridade _prioridade, boolean _ativo, List<Conteudo> _conteudos, Categoria _categoria){
		nome = _nome;
		imagem = _imagem;
		prioridade = _prioridade;
		ativo = _ativo;
		conteudos = _conteudos;
		categoria = _categoria;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}
	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
