package victor.pacotes.aplicativosrecuperacao.model;

public class Arquivo {
    String usuarioId;
    String nomeFoto;
    String referenciaImagem;

    public Arquivo(String usuarioId, String nomeFoto, String referenciaImagem) {
        this.usuarioId = usuarioId;
        this.nomeFoto = nomeFoto;
        this.referenciaImagem = referenciaImagem;
    }

    public Arquivo() {
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }

    public String getReferenciaImagem() {
        return referenciaImagem;
    }

    public void setReferenciaImagem(String referenciaImagem) {
        this.referenciaImagem = referenciaImagem;
    }
}
