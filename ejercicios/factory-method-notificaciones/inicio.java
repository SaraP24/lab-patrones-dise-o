interface Notificacion {
    void enviar(String destinatario, String mensaje);
}

class NotificacionCorreo implements Notificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        // TODO 1: mostrá un mensaje con este formato exacto:
        //         Correo enviado a [destinatario]: [mensaje]
        //         Usá los parámetros recibidos. Los corchetes no deben aparecer en la salida.
    }
}

class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {
        // TODO 2: mostrá un mensaje con este formato exacto:
        //         SMS enviado a [destinatario]: [mensaje]
        //         Respetá los espacios y los dos puntos.
    }
}

abstract class FabricaNotificacion {
    // Este es el Factory Method.
    // Cada fábrica concreta define qué notificación devuelve.
    public abstract Notificacion crearNotificacion();

    public void enviarNotificacion(String destinatario, String mensaje) {
        // TODO 3: llamá a crearNotificacion() y guardá el resultado en una variable de tipo Notificacion.
        //         Sobre esa variable, invocá enviar() pasándole destinatario y mensaje.
        //         No crees aquí NotificacionCorreo ni NotificacionSMS, ni uses un if para elegir:
        //         la fábrica concreta se encarga de decidir qué objeto crear.
    }
}

class FabricaCorreo extends FabricaNotificacion {
    @Override
    public Notificacion crearNotificacion() {
        // TODO 4: reemplazá el return null por un objeto nuevo de tipo NotificacionCorreo.
        return null;
    }
}

class FabricaSMS extends FabricaNotificacion {
    @Override
    public Notificacion crearNotificacion() {
        // TODO 5: reemplazá el return null por un objeto nuevo de tipo NotificacionSMS.
        return null;
    }
}
