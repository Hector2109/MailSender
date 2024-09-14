package com.mycompany.mailsender.servidores;

import jakarta.mail.Authenticator;

public interface ServidorCorreo {
    /** 
     * Metodo que se encarga de autentificar la sesion del usuario,
     * mediante su contraseña de aplicacion y correo.
     * @param email correo del usuario.
     * @param password contraseña de aplicacion.
     * @return falso si no son correctas, en caso contrario true.
     */
    boolean autentificacion(String email, String password);
    
    /** 
     * Metodo el cual envia un correo a un destinatario, este consta
     * de un asunto y mensaje a enviar.
     * @param auth Autentificador de envio.
     * @param emailFrom correo emisor.
     * @param mailTO correo del destinatario.
     * @param subject asunto del mensaje.
     * @param content contenido del mensaje.
     */
    void enviarCorreo(Authenticator auth, String emailFrom, String mailTO, String subject, String content);
}
