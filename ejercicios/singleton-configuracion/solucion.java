public class ConfiguracionSistema {
    private static ConfiguracionSistema instancia;

    private String idioma;

    private ConfiguracionSistema() {
        this.idioma = "Español";
    }

    public static ConfiguracionSistema getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionSistema();
        }
        return instancia;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
