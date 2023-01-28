package com.sber.java13spring.java13springproject.libraryproject.model;

public enum Genre {
    FANTASY("Фантастика"),
    SINCE_FICTION("Научная фантастика"),
    DRAMA("Драма"),
    NOVEL("Роман");
    private final String genreTextDisplay;
    Genre(String genreName) {
        this.genreTextDisplay = genreName;
    }
    
    public String getGenreTextDisplay() {
        return this.genreTextDisplay;
    }
}
