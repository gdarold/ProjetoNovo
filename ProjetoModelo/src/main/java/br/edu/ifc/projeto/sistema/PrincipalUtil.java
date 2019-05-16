/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.projeto.sistema;

/**
 *
 * @author fabricio
 */
public class PrincipalUtil {

    private static PrincipalUtil utilitario;
    private TelaPrincipal principal;

    public PrincipalUtil() {
    }

    public static PrincipalUtil getInstance() {
        if (utilitario == null) {
            utilitario = new PrincipalUtil();
        }

        return utilitario;
    }

    public void iniciarSistema() {
        if (this.principal == null) {
            this.principal = new TelaPrincipal();
        }
        this.principal.setVisible(true);
        this.principal.setEnabled(true);

    }

    public TelaPrincipal getPrincipal() {
        return principal;
    }

}
