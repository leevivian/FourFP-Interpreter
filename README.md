# FourFP-Interpreter

The purpose of this project is to apply concepts such as parsing, regular expressions, and abstract syntax trees into one application, which can draw shapes and do arithmetic. The FourFP Interpreter includes a command line that takes in statements from a user. The input is then parsed, interpreted, and some form of output is displayed on the view, given the user’s statement was valid (in terms of the FourFP language).

The FourFP Language is as follows:


1. Each line shall contain a statement.

 * Each statement shall end with a semicolon.
  
 * A single statement shall not exceed one line. 
  
2. A minimum of one whitespace shall separate each lexeme

 * Whitespace consists of either the space character or \t
  
 * Whitespace at the beginning of a line is ignored
  
 * Whitespace after a statement is ignored until the next statement begins
  
  
3. Comments are denoted with a # sign

 * Starting at the # sign, everything is ignored until a new line is encountered


4. Variable names shall consist of all lowercase letters

5. Variables shall be of only the integer type

6. Variables shall be declared and initialized in one line

 * Variables shall be initialized with a literal integer
  
 * The int keyword shall be used to declare the variable. For example, to declare the variable a:
  
       int a = 0;  
       
7. There shall be a predetermined number of styles

 * Styles shall be a combination of stroke color, stroke thickness, and fill color.
  
 * Styles shall be numbered, starting at 1
  
8. To draw shapes, the following instructions shall be defined

 * For each instruction, the arguments shall be either an integer literal or a variable name
  
 * To draw a circle:
  
         circle x y r s
         
         where x and y are the x and y coordinates
         
         r is the radius
         
         s is the style
       
 * To draw a rectangle:
  
       rect x1 y1 x2 y2 s
       
       where x1 y1 are the upper left x and y coordinates
       
       x2 y2 are the lower right x and y coordinates
       
       s is the style
       
       
9. Basic arithmetic expressions shall be able to be used

 * Arithmetic expressions shall be infix notated
   
  * left_operand operator right_operand
    
 * Grouping will be allowed using matched parentheses
  
 * The follow operators are allowed:
  
  *  (+) addition
    
  *  (–) subtraction
    
  *  (*) multiplication
    
  *  (/) division
