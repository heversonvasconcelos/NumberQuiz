package sampleapp.numberquiz.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Singleton utilizada na manipula��o de dados na sess�o.
 *
 * @author heverson.vasconcelos
 */
public class SessionUtil {

    /**
     * Armazena a sess�o corrente.
     */
    private static HttpSession session;

    /**
     * M�todo para recuperar um objeto (atributo) previamente salvo na sess�o.
     *
     * @param attributeName Nome do objeto (atributo) a ser recuperado.
     * @return Objeto recuperado.
     */
    public static Object getAttribute(String attributeName) {
        getRequestSession();
        return session.getAttribute(attributeName);
    }

    /**
     * M�todo para salvar um objeto (atributo) na sess�o.
     *
     * @param attributeName Nome do objeto (atributo) a ser salvo.
     * @param obj Objeto a ser salvo.
     */
    public static void setAttribute(String attributeName, Object obj) {
        getSession();
        session.setAttribute(attributeName, obj);
    }

    /**
     * M�todo para destruir a sess�o corrente.
     */
    public static void destroySession() {
        getSession();
        session.invalidate();
    }

    /**
     * M�todo para retornar a sess�o corrente.
     */
    private static void getSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
    }

    private static void getRequestSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        session = request.getSession();
    }
}
