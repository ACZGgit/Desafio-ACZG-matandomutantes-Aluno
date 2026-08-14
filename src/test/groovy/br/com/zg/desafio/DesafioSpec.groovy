package br.com.zg.desafio

import br.com.zg.desafio.harness.ImplementacaoAtual
import br.com.zg.desafio.modelo.*
import spock.lang.Specification
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Classe base do desafio. Estenda-a na sua suíte.
 *
 * `calculadora` é criada UMA vez por método de teste (comportamento padrão do
 * Spock). Se um cenário precisar de mais de uma chamada, use a mesma instância.
 */
abstract class DesafioSpec extends Specification {

    protected static final ZoneId SP = ZoneId.of('America/Sao_Paulo')

    protected CalculadoraRepasse calculadora = ImplementacaoAtual.novaInstancia()

    // ---------- fixtures ----------

    protected static ZonedDateTime momento(String isoLocal, ZoneId zona = SP) {
        ZonedDateTime.of(java.time.LocalDateTime.parse(isoLocal), zona)
    }

    protected static Internacao internacaoDe(String entrada, String saida, ZoneId zona = SP) {
        new Internacao(momento(entrada, zona), momento(saida, zona))
    }

    protected static Glosa glosa(String valor, String motivo = 'motivo generico') {
        new Glosa(motivo, new BigDecimal(valor))
    }

    /** Solicitação padrão: 12h de internação, convênio não parceiro, sem glosas. */
    protected static SolicitacaoRepasse solicitacao(Map ajustes = [:]) {
        Map p = [
            codigo     : 'PROC-001',
            valorTabela: '1000.00',
            entrada    : '2026-05-04T08:00:00',
            saida      : '2026-05-04T20:00:00',
            zona       : SP,
            convenio   : 'Convenio Avulso',
            glosas     : [],
        ] + ajustes

        new SolicitacaoRepasse(
            new Procedimento(p.codigo, p.valorTabela == null ? null : new BigDecimal(p.valorTabela as String)),
            internacaoDe(p.entrada as String, p.saida as String, p.zona as ZoneId),
            p.convenio as String,
            p.glosas as List<Glosa>
        )
    }
}
