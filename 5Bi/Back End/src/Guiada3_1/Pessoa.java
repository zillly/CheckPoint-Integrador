package Guiada3_1;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private  String sobrenome;
    private  int rg;
    private LocalDate dataAplicacao;
    private LocalDate dataAgendada;
    private  String nomeVacina;


    public Pessoa(String nome, String sobrenome, int rg, LocalDate dataAplicacao, LocalDate dataAgendada, String nomeVacina) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.dataAplicacao = dataAplicacao;
        this.dataAgendada = dataAgendada;
        this.nomeVacina = nomeVacina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }
}
