@SuppressWarnings("serial")

public class Pinguin extends Maze {

  static int[][] maze;


  public static boolean wall(int x, int y) {
    return maze[x][y] == WALL;
  }


  public static void walkhilf(int x, int y, int maxdistance, int penguin) {


    boolean obstacleunten = (wall(x, y + 1) || maze[x][y + 1] == OLD_PATH_DONE);
    boolean obstaclerechts = (wall(x + 1, y) || maze[x + 1][y] == OLD_PATH_DONE);
    boolean obstaclelinks = (wall(x - 1, y) || maze[x - 1][y] == OLD_PATH_DONE);
    boolean obstacleoben = (wall(x, y - 1) || maze[x][y - 1] == OLD_PATH_DONE);
    maze[x][y] = PLAYER;
    draw(maze);


    // wenn ich wieder am Ausgangspunkt bin und alle Pinguine eingesammelt habe
    if (obstaclelinks == true && obstaclerechts == true && (wall(x, y + 1)
        || maze[x][y + 1] == OLD_PATH_ACTIVE || maze[x][y + 1] == OLD_PATH_DONE) == true
        && maze[1][1] == PLAYER) { // zurück nach oben
      maze[1][1] = OLD_PATH_DONE;
      maze[1][0] = PLAYER;
      draw(maze);
      MiniJava.write("Sie haben das Labyrinth abgesucht und " + penguin + " Pinguine gerettet.");
      return;
    }

    // Wenn Maxdistance erreicht
    if (0 == maxdistance) {
      maxdistance += 1;
      maze[x][y] = OLD_PATH_DONE;
      if (maze[x - 1][y] == OLD_PATH_ACTIVE) { // zurück nach links
        walkhilf(x - 1, y, maxdistance, penguin);
      }
      if (maze[x][y + 1] == OLD_PATH_ACTIVE) { // zurück nach unten
        walkhilf(x, y + 1, maxdistance, penguin);
      }
      if (maze[x + 1][y] == OLD_PATH_ACTIVE) { // zurück nach rechts
        walkhilf(x + 1, y, maxdistance, penguin);
      }
      if (maze[x][y - 1] == OLD_PATH_ACTIVE) { // zurück nach oben
        walkhilf(x, y - 1, maxdistance, penguin);
      }
    }


    // Wenn nur Wand oder Old_Path_Done => Sackgasse
    if (obstaclelinks == true && obstacleoben == true && obstacleunten == true) { // zurück nach
                                                                                  // rechts
      maxdistance += 1;
      maze[x][y] = OLD_PATH_DONE;
      walkhilf(x + 1, y, maxdistance, penguin);
    }
    if (obstaclelinks == true && obstaclerechts == true && obstacleunten == true) { // zurück nach
                                                                                    // oben
      maxdistance += 1;
      maze[x][y] = OLD_PATH_DONE;
      walkhilf(x, y - 1, maxdistance, penguin);
    }
    if (obstacleoben == true && obstaclerechts == true && obstacleunten == true) { // zurück nach
                                                                                   // links
      maxdistance += 1;
      maze[x][y] = OLD_PATH_DONE;
      walkhilf(x - 1, y, maxdistance, penguin);
    }
    if (obstaclelinks == true && obstaclerechts == true && obstacleoben == true) { // zurück nach
                                                                                   // unten
      maxdistance += 1;
      maze[x][y] = OLD_PATH_DONE;
      walkhilf(x, y + 1, maxdistance, penguin);
    }

    // Wenn keine Wand, kein Old_Path_Done und kein Old_Path_Active
    if (maze[x - 1][y] == FREE || maze[x - 1][y] == PENGUIN) { // nach links
      if ((wall(x, y - 1) || wall(x, y + 1) || wall(x - 1, y + 1) || wall(x - 2, y)
          || wall(x - 1, y - 1) || wall(x - 2, y + 1) || wall(x - 2, y - 1)) && maxdistance > 0) {
        maxdistance -= 1;
        if (maze[x - 1][y] == PENGUIN) {
          penguin += 1;
        }
        maze[x][y] = OLD_PATH_ACTIVE;
        walkhilf(x - 1, y, maxdistance, penguin);
      }
    }
    if (maze[x][y + 1] == FREE || maze[x][y + 1] == PENGUIN) { // nach unten
      if ((wall(x - 1, y) || wall(x + 1, y) || wall(x - 1, y + 1) || wall(x, y + 2)
          || wall(x + 1, y + 1) || wall(x - 1, y + 2) || wall(x + 1, y + 2)) && maxdistance > 0) {
        maxdistance -= 1;
        if (maze[x][y + 1] == PENGUIN) {
          penguin += 1;
        }
        maze[x][y] = OLD_PATH_ACTIVE;
        walkhilf(x, y + 1, maxdistance, penguin);
      }
    }
    if (maze[x + 1][y] == FREE || maze[x + 1][y] == PENGUIN) { // nach rechts
      if ((wall(x, y + 1) || wall(x, y - 1) || wall(x + 1, y + 1) || wall(x + 2, y)
          || wall(x + 1, y - 1) || wall(x + 2, y + 1) || wall(x + 2, y - 1)) && maxdistance > 0) {
        maxdistance -= 1;
        if (maze[x + 1][y] == PENGUIN) {
          penguin += 1;
        }
        maze[x][y] = OLD_PATH_ACTIVE;
        walkhilf(x + 1, y, maxdistance, penguin);
      }
    }
    if (maze[x][y - 1] == FREE || maze[x][y - 1] == PENGUIN) { // nach oben
      if ((wall(x - 1, y) || wall(x + 1, y) || wall(x - 1, y - 1) || wall(x, y - 2)
          || wall(x + 1, y - 1) || wall(x - 1, y - 2) || wall(x + 1, y - 2)) && maxdistance > 0) {
        maxdistance -= 1;
        if (maze[x][y - 1] == PENGUIN) {
          penguin += 1;
        }
        maze[x][y] = OLD_PATH_ACTIVE;
        walkhilf(x, y - 1, maxdistance, penguin);
      }
    }


    // Wenn nur Wand, OLD Path Done oder Old Path Active
    if ((obstaclelinks == true || maze[x - 1][y] == OLD_PATH_ACTIVE)
        && (obstacleunten == true || maze[x][y + 1] == OLD_PATH_ACTIVE)
        && (obstaclerechts == true || maze[x + 1][y] == OLD_PATH_ACTIVE)
        && (obstacleoben == true || maze[x][y - 1] == OLD_PATH_ACTIVE)) {
      if (maze[x - 1][y] == OLD_PATH_ACTIVE) { // zurück nach links
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x - 1, y, maxdistance, penguin);
      }
      if (maze[x][y + 1] == OLD_PATH_ACTIVE) { // zurück nach unten
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x, y + 1, maxdistance, penguin);
      }
      if (maze[x + 1][y] == OLD_PATH_ACTIVE) { // zurück nach rechts
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x + 1, y, maxdistance, penguin);
      }
      if (maze[x][y - 1] == OLD_PATH_ACTIVE) { // zurück nach oben
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x, y - 1, maxdistance, penguin);
      }
    }


    // Wenn FREE nicht geht, weil nirgends eine Wand ist und ich zurückmuss
    if ((obstaclelinks == true || maze[x - 1][y] == OLD_PATH_ACTIVE || maze[x - 1][y] == FREE)
        && (obstacleunten == true || maze[x][y + 1] == OLD_PATH_ACTIVE || maze[x][y + 1] == FREE)
        && (obstaclerechts == true || maze[x + 1][y] == OLD_PATH_ACTIVE || maze[x + 1][y] == FREE)
        && (obstacleoben == true || maze[x][y - 1] == OLD_PATH_ACTIVE || maze[x][y - 1] == FREE)) {
      if (maze[x - 1][y] == OLD_PATH_ACTIVE) { // zurück nach links
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x - 1, y, maxdistance, penguin);
      }
      if (maze[x][y + 1] == OLD_PATH_ACTIVE) { // zurück nach unten
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x, y + 1, maxdistance, penguin);
      }
      if (maze[x + 1][y] == OLD_PATH_ACTIVE) { // zurück nach rechts
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x + 1, y, maxdistance, penguin);
      }
      if (maze[x][y - 1] == OLD_PATH_ACTIVE) { // zurück nach oben
        maxdistance += 1;
        maze[x][y] = OLD_PATH_DONE;
        walkhilf(x, y - 1, maxdistance, penguin);
      }



    }
  }


  public static void walk(int x, int y, int maxdistance) {
    int penguin = 0;
    maze[1][0] = PLAYER;
    draw(maze);

    if (maze[1][1] == WALL) {
      MiniJava.write("Das Labyrinth kann nicht betreten werden.");
      return;
    } else {
      if (maze[x][y + 1] == PENGUIN) {
        penguin += 1;
      }
      maze[x][y] = OLD_PATH_ACTIVE;
    }
    walkhilf(x, y + 1, maxdistance - 1, penguin);
    return;

  }



  public static void main(String[] args) {
    int width = MiniJava.read("Bitte geben Sie eine Länge für Ihr Feld ein:");
    while (width <= 0) {
      width = MiniJava.read("Die Zahl muss größer 0 sein:");
    }
    int height = MiniJava.read("Bitte geben Sie eine Höhe für Ihr Feld ein:");
    while (height <= 0) {
      height = MiniJava.read("Die Zahl muss größer 0 sein:");
    }
    int maxdistance = MiniJava.read("Bitte geben Sie Ihre maximale Distanz ein:");
    while (maxdistance <= 0) {
      maxdistance = MiniJava.read("Die Zahl muss größer 0 sein:");
    }

    maze = generateStandardPenguinMaze(width, height);
    walk(1, 0, maxdistance);

  }

}
