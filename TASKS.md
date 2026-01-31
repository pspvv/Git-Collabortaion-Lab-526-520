perform tests on tic tac toe game

## Example Test Cases

1. **Test Win Condition - Horizontal**
   - Place X at positions (0,0), (0,1), (0,2)
   - Expected: X wins

2. **Test Win Condition - Vertical**
   - Place O at positions (0,0), (1,0), (2,0)
   - Expected: O wins

3. **Test Win Condition - Diagonal**
   - Place X at positions (0,0), (1,1), (2,2)
   - Expected: X wins

4. **Test Draw Condition**
   - Fill all 9 cells with no winner
   - Expected: Game ends in draw

5. **Test Invalid Move**
   - Try placing a mark on an occupied cell
   - Expected: Move rejected, prompt for new input

6. **Test Turn Alternation**
   - X plays first, then O, then X
   - Expected: Turns alternate correctly