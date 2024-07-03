package com.aluracursos.challengeliteralura.models;

public enum Idioma {
    en("[en]", "Ingles"),
    es("[es]", "Español"),
    fr("[fr]", "Frances"),
    pt("[pt]", "Portugues");

    private String idiomaGutendex;
    private String idiomaEspaniol;

    /*
    // Mapas estáticos para búsqueda rápida
    private static final Map<String, Idioma> GUTENDEX_MAP = new HashMap<>();
    private static final Map<String, Idioma> ESPANOL_MAP = new HashMap<>();

    static {
        for (Idioma idioma : Idioma.values()) {
            GUTENDEX_MAP.put(idioma.idiomaGutendex.toLowerCase(), idioma);
            ESPANOL_MAP.put(idioma.idiomaEspanol.toLowerCase(), idioma);
        }
    }
    */

    Idioma(String idiomaGutendex, String idiomaEspaniol) {
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspaniol = idiomaEspaniol;
    }

    public static Idioma fromString(String text){
        for (Idioma idioma: Idioma.values()){
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }

    public static Idioma fromEspanol(String text){
        for (Idioma idioma: Idioma.values()){
            if (idioma.idiomaEspaniol.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }

}
