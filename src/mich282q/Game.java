package mich282q;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    // Definerer hvor stort vores array med celler skal være
    private final int BOARD_SIZE_X = 40, BOARD_SIZE_Y = 40;

    // 2 dimensionelt array med celler
    private Cell[][] cellsArray = new Cell[BOARD_SIZE_X][BOARD_SIZE_Y];

    // Laver levende og døde celler til arrayet når Game objektet bliver oprettet
    private Game() {

        for (int y = 0; y < BOARD_SIZE_Y; y++) {
            for (int x = 0; x < BOARD_SIZE_X; x++) {

                Random random = new Random();
                int randomNumber = random.nextInt(2);
                Cell cell = new Cell();

                if (randomNumber == 0) {
                    cell.setAlive(false);
                } else if (randomNumber == 1) {
                    cell.setAlive(true);
                }

                cellsArray[x][y] = cell;
            }
        }
    }

    // Metode der returnerer et Game objekt
    public static Game createGame() {
        Game game = new Game();
        return game;
    }

    private Rectangle rectangleBlue, rectangleWhite;
    private ArrayList<Rectangle> rectangles = new ArrayList<>();

    // Metode der løber arrayet igennem og tegner cellerne
    public void drawCells() {
        for (int y = 0, yPosition = 25; y < BOARD_SIZE_Y; y++, yPosition = yPosition + 22) {
            for (int x = 0, xPosition = 25; x < BOARD_SIZE_X; x++, xPosition = xPosition + 22) {

                if (cellsArray[x][y].isAlive()) {
                    rectangleBlue = new Rectangle(20, 20, Color.BLUE);
                    rectangleBlue.setX(xPosition);
                    rectangleBlue.setY(yPosition);
                    rectangleBlue.setStrokeType(StrokeType.INSIDE);
                    rectangleBlue.setStroke(Color.BLACK);
                    rectangleBlue.setStrokeWidth(2);
                    GameOfLifeGUI.getPane().getChildren().add(rectangleBlue);
                    rectangles.add(rectangleBlue);
                }

                if (!cellsArray[x][y].isAlive()) {
                    rectangleWhite = new Rectangle(20, 20, Color.PURPLE);
                    rectangleWhite.setX(xPosition);
                    rectangleWhite.setY(yPosition);
                    rectangleWhite.setStrokeType(StrokeType.INSIDE);
                    rectangleWhite.setStroke(Color.RED);
                    rectangleWhite.setStrokeWidth(2);
                    GameOfLifeGUI.getPane().getChildren().add(rectangleWhite);
                    rectangles.add(rectangleWhite);
                }

            }
        }

    }

    // En update metode der først løber hele arrayet igennem og sætter hver celles livingNeighbours,
    // dernæst løber arrayet igennem og kalder update() på hver celle.
    public void update() {

        for (int y = 0; y < BOARD_SIZE_Y; y++) {
            for (int x = 0; x < BOARD_SIZE_X; x++) {

                cellsArray[x][y].setLivingNeighbours(0);

                if (x > 0 && y > 0) {
                    if (cellsArray[x - 1][y - 1].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (x > 0) {
                    if (cellsArray[x - 1][y].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (y < BOARD_SIZE_Y - 1 && x > 0) {
                    if (cellsArray[x - 1][y + 1].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (y < BOARD_SIZE_Y - 1) {
                    if (cellsArray[x][y + 1].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (x < BOARD_SIZE_X - 1 && y < BOARD_SIZE_Y - 1) {
                    if (cellsArray[x + 1][y + 1].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (x < BOARD_SIZE_X - 1) {
                    if (cellsArray[x + 1][y].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (y > 0) {
                    if (cellsArray[x][y - 1].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }

                if (x < BOARD_SIZE_X - 1 && y > 0) {
                    if (cellsArray[x + 1][y - 1].isAlive()) {
                        cellsArray[x][y].setLivingNeighbours(cellsArray[x][y].getLivingNeighbours() + 1);
                    }
                }
            }
        }

        for (int y = 0; y < BOARD_SIZE_Y; y++) {
            for (int x = 0; x < BOARD_SIZE_X; x++) {
                cellsArray[x][y].update();
            }
        }
    }

    private int counter = 0;

    // Metode til hvad der sker når man trykker på knappen
    public void buttonAction() {
        GameOfLifeGUI.getPane().getChildren().removeAll(rectangles);

        rectangles.clear();

        update();

        drawCells();

        counter++;

        String counterToString = String.valueOf(counter);

        GameOfLifeGUI.getLabel().setText(counterToString);
    }
}
