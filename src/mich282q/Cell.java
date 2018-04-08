package mich282q;

public class Cell {
    // Deklaration af datatyper
    private int livingNeighbours;
    private boolean alive;

    /*
     **  Værdier til state og hvad de betyder:
     **  1 = birth
     **  2 = survival
     **  3 = overcrowding or loneliness
     */

    private byte state;

    // Update metode, der sætter alive til true eller false ud fra reglerne
    public void update() {

        // Hvis cellen er død og har 3 levende naboer, bliver den til en levende celle (birth)
        if (!isAlive() && livingNeighbours == 3) {
            alive = true;
            state = 1;
        }
        // Hvis cellen er levende og har 2 eller 3 levende naboer så er den stadig levende (survival)
        else if (isAlive() && (livingNeighbours == 2 || livingNeighbours == 3)) {
            alive = true;
            state = 2;
        }
        // Ellers dør den eller er stadig død (overcrowding or loneliness)
        else {
            alive = false;
            state = 3;
        }

    }

    // Gætter til livingNeighbours
    public int getLivingNeighbours() {
        return livingNeighbours;
    }

    // Sætter til livingNeighbours
    public void setLivingNeighbours(int livingNeighbours) {
        this.livingNeighbours = livingNeighbours;
    }

    // Gætter til alive
    public boolean isAlive() {
        return alive;
    }

    // Sætter til alive
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Gætter til state
    public byte getState() {
        return state;
    }

    // Sætter til state
    public void setState(byte state) {
        this.state = state;
    }
}
