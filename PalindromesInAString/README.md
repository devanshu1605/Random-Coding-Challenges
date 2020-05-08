The algorithm provided in Solution.java takes String as input and returns all Palindromes greater than size 3, in given string and the count of palindromes.

Algorithm:
1. input String str : aabaabbaabb
2. Take a character.
3. traverse left and right of that character and compare for equality. 
4. keep comparing till inequality is found.
5. Add the substring found to a set.
6. move to next character and continue steps 2-4
7. Repeat step 5 till length of string is reached.