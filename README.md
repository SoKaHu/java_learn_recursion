# java_learn_recursion
Learning Java part 5 - recursion

This was coded as part of a TUM introduction in programming course.
The program is a UTF8-coded java file. The Maze file was provided by the chair of Algorithms and Complexity of TU Munich.

Instructions:

In this task you should help all these poor penguins out of the labyrinth. 
Start at the starting point, leave the food there. Search penguins and bring them back there. Never walk on fields that are not next to a wall in the surrounding 8 fields. But there is a maximum distance from the starting point you can go, in case you get tired. Never go further than your max distance. 
Use recursion for your walk method. Use the method draw(int[][] labyrinth) to mark your way. 
Use a twodimensional array that saves a value what can be found on this field:  
• WALL: At this position is a wall. 
• FREE: At this position is nothing. 
• PLAYER: At this position is the heroic rescuer. 
• OLD_PATH_ACTIVE: This field has already been visited before and you come back to search for another route.
• OLD_PATH_DONE: This field has already been visited before but you don't return. 
• PENGUIN: At this position is a penguin. 
Carry penguins by replacing them with OLD_PATH_ACTIVE or OLD_PATH_DONE.
Implement a main program which asks the user for the labyrinth size and the maximum distance. Start at (1, 0). At the end, output how many penguins you saved. 
