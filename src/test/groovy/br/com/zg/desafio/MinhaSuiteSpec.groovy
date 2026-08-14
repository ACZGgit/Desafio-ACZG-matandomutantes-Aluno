package br.com.zg.desafio

import spock.lang.Unroll

/**
 * SUA ENTREGA.
 *
 * Renomeie a classe (e o arquivo) para MinhaSuite<SeuNome>Spec.
 * Exemplo: MinhaSuiteJoanaSpec.groovy
 *
 * Regras:
 *  - use somente o contrato público: calculadora.calcular(...)
 *  - nunca instancie uma implementação diretamente — use o campo `calculadora`
 *  - todo bloco `then:` / `expect:` precisa de ao menos uma asserção real
 *  - a suíte inteira precisa passar contra a referência (rode ./verificar.sh)
 *
 * Os três testes abaixo existem só para mostrar a sintaxe do Spock.
 * Eles não valem ponto. Pode apagá-los.
 */
class MinhaSuiteSpec extends DesafioSpec {

    // ---- bloco when/then ----
    def "repassa o valor de tabela quando não há adicional, desconto nem glosa"() {
        when:
        def resultado = calculadora.calcular(solicitacao())

        then:
        resultado == new BigDecimal('1000.00')
    }

    // ---- bloco where: uma tabela de casos, um teste por linha ----
    @Unroll
    def "glosas de #descricao reduzem o repasse para #esperado"() {
        expect:
        calculadora.calcular(solicitacao(glosas: glosas)) == new BigDecimal(esperado)

        where:
        descricao       | glosas                            || esperado
        'nenhuma'       | []                                || '1000.00'
        'uma glosa'     | [glosa('250.00')]                 || '750.00'
        'duas glosas'   | [glosa('250.00'), glosa('50.00')] || '700.00'
    }

    // ---- exceções ----
    def "convênio em branco é entrada inválida"() {
        when:
        calculadora.calcular(solicitacao(convenio: '   '))

        then:
        thrown(DadosInvalidosException)
    }

    // ==================================================================
    // A partir daqui é com você.
    // ==================================================================
}
