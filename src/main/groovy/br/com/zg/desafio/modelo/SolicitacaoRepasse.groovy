package br.com.zg.desafio.modelo

import groovy.transform.Canonical

/**
 * ATENÇÃO: a lista de glosas NÃO é copiada defensivamente.
 * É a mesma referência que o chamador passou.
 */
@Canonical
class SolicitacaoRepasse {
    Procedimento procedimento
    Internacao internacao
    String convenio
    List<Glosa> glosas
}
