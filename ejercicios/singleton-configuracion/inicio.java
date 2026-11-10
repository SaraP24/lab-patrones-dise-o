public class ConfiguracionSistema {
    // Al ser static, este atributo pertenece a la clase
    // y guarda la referencia a la instancia compartida.
    private static ConfiguracionSistema instancia;

    private String idioma;

    private ConfiguracionSistema() {
        // TODO 1: asigná "Español" al atributo idioma.
        //
        // El constructor es privado para impedir que otras clases
        // creen objetos directamente con new ConfiguracionSistema().
    }

    public static ConfiguracionSistema getInstancia() {
        // TODO 2: comprobá con un if si instancia contiene null.
        //         Solo en ese caso creá un nuevo ConfiguracionSistema y guardalo en instancia.
        // TODO 3: después de la comprobación, devolvé instancia.
        return null;
    }

    public String getIdioma() {
        // TODO 4: devolvé el valor actual del atributo idioma.
        return null;
    }

    public void setIdioma(String idioma) {
        // TODO 5: guardá el idioma recibido usando this.
    }
}
