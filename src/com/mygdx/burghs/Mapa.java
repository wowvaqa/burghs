package com.mygdx.burghs;

// Klasa Mapa przechowuje obiekty Klasy Pole
import enums.TypyTerenu;
import java.io.Serializable;

public final class Mapa implements Serializable {

    private int iloscPolX = 10;
    private int iloscPolY = 10;

    public Pole[][] pola;

    /**
     * Tworzy mapę domyślną 10x10 pól
     */
    public Mapa() {
        this.generujMape(iloscPolX, iloscPolY);
    }

    /**
     * Tworzy mapę z zadeklarowanych wartości ilości pól.
     *
     * @param iloscPolX Ilość pól w osi X
     * @param iloscPolY Ilość pól w osi Y
     */
    public Mapa(int iloscPolX, int iloscPolY) {
        this.iloscPolX = iloscPolX;
        this.iloscPolY = iloscPolY;
        this.generujMape(this.iloscPolX, this.iloscPolY);
    }

    /**
     * Generuje mapę z zadanych ilości pól.
     *
     * @param iloscPolX
     * @param iloscPolY
     */
    public void generujMape(int iloscPolX, int iloscPolY) {
        this.iloscPolX = iloscPolX;
        this.iloscPolY = iloscPolY;
        pola = new Pole[iloscPolX][iloscPolY];

        for (int i = 0; i < this.iloscPolX; i++) {
            for (int j = 0; j < this.iloscPolY; j++) {
                pola[i][j] = new Pole();
            }
        }
    }

    /**
     * Zwraca w postaci Stringa odpowiednią nazwę TextureRegiona rzeki.
     *
     * @param x Współżędne X pola na mapie.
     * @param y Współżędne X pola na mapie.
     * @param mapa Referencja do obiektu mapy.
     * @param tT Referencja do TypuTerenu
     * @return
     */
    public static String getTextureRegion(int x, int y, Mapa mapa, TypyTerenu tT) {

        // Prawy górny róg mapy
        if ("PG".equals(getPartOfMap(x, y, mapa))) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverNS";
            }
        } // Lewy górny róg mapy  
        else if ("LG".equals(getPartOfMap(x, y, mapa))) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverNS";
            }
        } // Prawy dolny róg mapy
        else if ("PD".equals(getPartOfMap(x, y, mapa))) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverNS";
            }
        } // Lewy dolny róg mapy
        else if ("LD".equals(getPartOfMap(x, y, mapa))) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverNS";
            }
        } // Góra MAPY
        else if ("G".equals(getPartOfMap(x, y, mapa))) {
            //SOUTH
            if (mapa.getPola()[x][y - 1].getTypTerenu() == tT) {
                if (tT == TypyTerenu.Drzewo) {
                    return "forestS";
                } else {
                    return "riverNS";
                }
            }
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverNS";
            }
            // Dół MAPY
        } else if ("D".equals(getPartOfMap(x, y, mapa))) {
            if (mapa.getPola()[x][y + 1].getTypTerenu() == tT) {
                if (tT == TypyTerenu.Drzewo) {
                    return "forestN";
                } else {
                    return "riverNS";
                }
            }
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverNS";
            }
            // Lewa krawędź MAPY
        } else if ("L".equals(getPartOfMap(x, y, mapa))) {
            if (mapa.getPola()[x + 1][y].getTypTerenu() == tT) {
                if (tT == TypyTerenu.Drzewo) {
                    return "forestE";
                } else {
                    return "riverWE";
                }
            }
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverWE";
            }
            // Prawa krawędź MAPY
        } else if ("P".equals(getPartOfMap(x, y, mapa))) {
            if (mapa.getPola()[x - 1][y].getTypTerenu() == tT) {
                if (tT == TypyTerenu.Drzewo) {
                    return "forestW";
                } else {
                    return "riverWE";
                }
            }
            if (tT == TypyTerenu.Drzewo) {
                return "forestC";
            } else {
                return "riverWE";
            }
            // Środek mapy
        } else if ("C".equals(getPartOfMap(x, y, mapa))) {
            return getRegionC(x, y, mapa, tT);
        }
        if (tT == TypyTerenu.Drzewo) {
            return "forestC";
        } else {
            return "riverNS";
        }
    }

    private static String getRegionC(int x, int y, Mapa mapa, TypyTerenu tT) {
        // NORTH & SOUTH & WEST & EAST
        if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT
                && mapa.getPola()[x + 1][y].getTypTerenu() == tT
                && mapa.getPola()[x - 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNSWE";
            } else {
                return "riverNS";
            }
        } // NORTH & SOUTH & EAST
        else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT
                && mapa.getPola()[x + 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNSE";
            } else {
                return "riverNS";
            }
            // NORT & SOUT & WEST
        } else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT
                && mapa.getPola()[x - 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNSW";
            } else {
                return "riverNS";
            }
            // NORT & WEST & EAST
        } else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x + 1][y].getTypTerenu() == tT
                && mapa.getPola()[x - 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNWE";
            } else {
                return "riverNS";
            }
            // WEST & EAST & SOUTH
        } else if (mapa.getPola()[x - 1][y].getTypTerenu() == tT
                && mapa.getPola()[x + 1][y].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestWES";
            } else {
                return "riverNS";
            }
        } // NORTH & SOUTH
        else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNS";
            } else {
                return "riverNS";
            }
            // NORTH & EAST
        } else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x + 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNE";
            } else {
                return "riverNE";
            }
            // NORTH & WEST
        } else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT
                && mapa.getPola()[x - 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestNW";
            } else {
                return "riverNW";
            }
            // WEST & SOUTH
        } else if (mapa.getPola()[x - 1][y].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestWS";
            }
            return "riverWS";
            // EAST & SOUTH
        } else if (mapa.getPola()[x + 1][y].getTypTerenu() == tT
                && mapa.getPola()[x][y - 1].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestES";
            } else {
                return "riverES";
            }
            // WEST & EAST
        } else if (mapa.getPola()[x - 1][y].getTypTerenu() == tT
                && mapa.getPola()[x + 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestWE";
            } else {
                return "riverWE";
            }
            // NORTH
        } else if (mapa.getPola()[x][y + 1].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestN";
            } else {
                return "riverNS";
            }
            // SOUTH
        } else if (mapa.getPola()[x][y - 1].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestS";
            } else {
                return "riverNS";
            }
            // WEST
        } else if (mapa.getPola()[x - 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestW";
            } else {
                return "riverNS";
            }
            // EAST
        } else if (mapa.getPola()[x + 1][y].getTypTerenu() == tT) {
            if (tT == TypyTerenu.Drzewo) {
                return "forestE";
            } else {
                return "riverNS";
            }
        }
        if (tT == TypyTerenu.Drzewo) {
            return "forestNSWE";
        } else {
            return "riverNS";
        }
    }

    /**
     * Zwraca Stringa informujacego w jakiej części mapy znajduje się pole.
     *
     * @param x Współżędna X pola na mapie.
     * @param y Współżędna Y pola na mapie.
     * @param mapa Referencja do obiektu mapy.
     * @return D - dolna krawędź mapy. G - Górna krawędź mapy. S - Środek mapy.
     * L - Lewa krawędź mapy. P - Prawa krawędź mapy
     */
    private static String getPartOfMap(int x, int y, Mapa mapa) {
        if (x == 0 && y == 0) {
            System.out.println("Lewy Dolny róg mapy.");
            return "LD";
        } else if (x == mapa.getIloscPolX() - 1 && y == 0) {
            System.out.println("Prawy Dolny róg mapy.");
            return "PD";
        } else if (x == mapa.getIloscPolX() - 1 && y == mapa.getIloscPolY() - 1) {
            System.out.println("Prawy Górny róg mapy.");
            return "PG";
        } else if (x == 0 && y == mapa.getIloscPolY() - 1) {
            System.out.println("Lewy Górny róg mapy.");
            return "LG";
        } else if (x == 0) {
            System.out.println("Lewa krawędź mapy.");
            return "L";
        } else if (x == mapa.getIloscPolX() - 1) {
            System.out.println("Prawa krawędź mapy.");
            return "P";
        } else if (y == 0) {
            System.out.println("Dolna krawędź mapy.");
            return "D";
        } else if (y == mapa.getIloscPolY() - 1) {
            System.out.println("Górna krawędź mapy.");
            return "G";
        }
        return "C";
    }

    //SETTERS AND GETTERS
    public int getIloscPolX() {
        return iloscPolX;
    }

    public int getIloscPolY() {
        return iloscPolY;
    }

    public Pole[][] getPola() {
        return pola;
    }

    public void setPola(Pole[][] pola) {
        this.pola = pola;
    }
}
