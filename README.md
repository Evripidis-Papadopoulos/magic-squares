

1. **The basic algorithm** concerns magic squares that use the numbers from 1 to 3, also referred to as a 3×3 Sudoku.

2. **The rules** state that in each row and in each column, all numbers from 1 to 3 must appear. No number may be repeated within the same row or within the same column.

3. **The basic construction method** starts with the placement of one of the numbers 1, 2, or 3 in a randomly chosen cell of the square.

4. Next, a **different number** is placed in another randomly chosen cell, which must be located in a **different row and a different column** from the cell containing the first number.

5. After these initial placements, **the remaining numbers are placed deterministically**, based on the rules described above.

6. **Naturally, there are variations** of this method, which may differ in the initial random placement or in the deterministic completion process.


