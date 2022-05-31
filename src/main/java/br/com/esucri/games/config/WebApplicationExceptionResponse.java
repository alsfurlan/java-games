package br.com.esucri.games.config;

public class WebApplicationExceptionResponse {
    
    private String erro;

    public WebApplicationExceptionResponse(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
}
