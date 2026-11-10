interface Notificacion {
    void enviar(String destinatario, String mensaje);
}

class NotificacionCorreo implements Notificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("Correo enviado a " + destinatario + ": " + mensaje);
    }
}

class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("SMS enviado a " + destinatario + ": " + mensaje);
    }
}

abstract class FabricaNotificacion {
    // Factory Method: cada fábrica concreta decide qué notificación crear
    public abstract Notificacion crearNotificacion();

    public void enviarNotificacion(String destinatario, String mensaje) {
        Notificacion notificacion = crearNotificacion();
        notificacion.enviar(destinatario, mensaje);
    }
}

class FabricaCorreo extends FabricaNotificacion {
    @Override
    public Notificacion crearNotificacion() {
        return new NotificacionCorreo();
    }
}

class FabricaSMS extends FabricaNotificacion {
    @Override
    public Notificacion crearNotificacion() {
        return new NotificacionSMS();
    }
}
