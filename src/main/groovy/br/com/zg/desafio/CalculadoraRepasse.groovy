package br.com.zg.desafio

import br.com.zg.desafio.modelo.SolicitacaoRepasse

/**
 * Calcula o valor de repasse de um procedimento hospitalar.
 * Esta é a única superfície pública do desafio: seus testes devem
 * exercitar exclusivamente este contrato.
 */
interface CalculadoraRepasse {
    BigDecimal calcular(SolicitacaoRepasse solicitacao)
}
