import java.util.Optional;

public class Material {
	private String nome,link;

	public Material(String _nome){
		nome = _nome;
	}

	public Material(String _nome, String _link){
		nome = _nome;
		link = _link;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Optional<String> getLink() {
		return Optional.ofNullable(link);
	}
	public void setLink(String link) {
		this.link = link;
	}
}
