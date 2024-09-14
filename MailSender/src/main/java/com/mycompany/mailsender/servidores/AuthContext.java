package com.mycompany.mailsender.servidores;

import jakarta.mail.Authenticator;

public class AuthContext {
    private ServidorCorreo servidor;

    /** 
     * Constructor por defecto.
     */
    public AuthContext() {
    }
    
    /** 
     * Constructor el cual inicializa el servidor, con el servidor deseado.
     * @param servidor servicio elegido.
     * 
     */
    public AuthContext(ServidorCorreo servidor) {
        this.servidor = servidor;
    }
    
    public boolean autentificacionSesion(ServidorCorreo servidor, String email, String password) {
        if (servidor != null) {
            return servidor.autentificacion(email, password);
        }
        return false;
    }
    
    public void enviarCorreo(Authenticator auth, String emailFrom, String mailTO, String subject, String content){
        if (servidor != null) {
            servidor.enviarCorreo(auth, emailFrom, mailTO, subject, content);
        }
    }
}
