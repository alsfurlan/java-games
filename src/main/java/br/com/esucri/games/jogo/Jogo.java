package br.com.esucri.games.jogo;

import br.com.esucri.games.plataforma.Plataforma;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "jogos", schema = "public")
@SequenceGenerator(name = "JOGO_SEQ", sequenceName = "JOGO_SEQ")
public class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOGO_SEQ")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Column(nullable = false)
    private Float preco;

    @Column(nullable = false)
    private String logo;

    private LocalDate lancamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "plataformas_jogos",
            joinColumns = @JoinColumn(name = "id_jogo"),
            inverseJoinColumns = @JoinColumn(name = "id_plataforma"),
            foreignKey = @ForeignKey(name = "fk_jogo"),
            inverseForeignKey = @ForeignKey(name = "fk_plataforma")
    )
    private List<Plataforma> plataformas;

    public Jogo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

}
