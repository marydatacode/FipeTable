package br.com.alura.FipTable.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(@JsonAlias("codigo") String code,
                   @JsonAlias("nome") String name) {
}
