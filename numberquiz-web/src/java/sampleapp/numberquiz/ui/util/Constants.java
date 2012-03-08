package sampleapp.numberquiz.ui.util;

/**
 * Classe de defini��o de constantes utilizadas em diversas partes da aplica��o.
 *
 * @author heverson.vasconcelos
 */
public class Constants {

    /*
     * Constantes com o endere�o das p�ginas (relativos a raiz)
     */
    /**
     * Armazena o endere�o da p�gina de login (p�gina inicial).
     */
    public static final String PAGE_INDEX = "/index.xhtml";
    /**
     * Armazena o endere�o da p�gina onde o quiz sera apresentado ao usuario.
     */
    public static final String PAGE_NUMBERQUIZ = "/numberquiz.xhtml";
    /**
     * Armazena o endere�o da p�gina onde ser�o apresentadas ao usu�rio a sua
     * pontua��o final e as melhores pontua��es entre os jogos j� realizados.
     */
    public static final String PAGE_STATS = "/stats.xhtml";
    /**
     * Armazena o endere�o da p�gina que ir� apresentar ao usu�rio uma mensagem
     * alertando-o que n�o est� devidamente autenticado e logado.
     */
    public static final String PAGE_USER_NOT_LOGGED = "/usernotlogged.xhtml";

    /*
     * Mensagens de erro
     */
    public static final String MSG_INVALID_USER = "Usu�rio inv�lido";
    public static final String MSG_INVALID_PASSWORD = "Senha inv�lida";
    public static final String MSG_USER_ALREADY_EXISTS = "Usu�rio j� foi cadastrado";

    /*
     * Algumas constantes de configura��es
     */
    public static final String LOGGED_USER = "loggedUser";
    /**
     * Armazena a quantidade m�xima de itens que a lista com as melhores
     * pontua��es poder� ter.
     */
    public static final int CONFIG_NUMBER_OF_SCORES = 5;
}
