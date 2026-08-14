package br.com.zg.desafio.modelo

import groovy.transform.Canonical
import java.time.ZonedDateTime

@Canonical
class Internacao {
    ZonedDateTime entrada
    ZonedDateTime saida
}
