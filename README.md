# boggle_game_java

## OVERVIEW
In the game of Boggle, you are given a grid of letters and are asked to report as many words as
possible from a path among the letters. A path starts at one location in the grid and goes to the next
letter in any direction (left, right, up, down, or diagonally) to a direct neighbor. The letters along
the path should be a word from a known dictionary. You may not use a letter more than once in
any word.
In my approach to solving a Boggle Puzzle, I decided to use a Prefix Tree or Trie to store the words
obtained from the dictionary into a Trie structure for faster processing.
A trie is a tree-like data structure whose nodes store the letters of an alphabet. By structuring the
nodes in a particular way, words and strings can be retrieved from the structure by traversing down
a branch path of the tree.
Each trie has an empty root node, with links (or references/pointers) to other nodes — one for
each possible alphabetic value. The shape and the structure of a trie is always a set of linked nodes,
connecting back to an empty root node. An important thing to note is that the number of child
nodes in a trie depends completely upon the total number of values possible. For example, if we
are representing the English alphabet, then the total number of child nodes is directly connected to
the total number of letters possible. In the English alphabet, there are 26 letters, so the total number
of child nodes will be 26. In this way, the size of a trie is directly correlated to the size of all the
possible values that the trie could represent.
A single node in a trie contains just two things:
- A value, which might be null
- An array of references to child nodes, all of which also might be null.
Each node in a trie, including the root node itself, has only these two aspects to it. Nodes in a trie
do not store entire keywords, instead, they store a part of the key (usually a character of the string).
When we traverse down from the root node to the leaf node, we can build the keyword from these
small parts of the key. The length of the words determines the depth of the Trie.

## FILES AND DATA STRUCTURES
For implementing the above, I have created the Classes listed below along with their public
methods:
1. Boggle.java - This class accepts a dictionary of words and a puzzle grid (not necessarily
   square) and provides a sorted list of all the words found in the puzzle in a specific format.
   Public Methods:
   - getDictionary - This method reads a set of words that are candidates for the solution.
   Each word will be on a line of its own. The end of the words is signaled either by
   the end-of-file marker on the stream or by a blank line in the stream. This method
   returns true if the words are all read and ready to use for puzzle-solving.
   - getPuzzle - This method reads a rectangular grid of letters (MxN) that form the
   Boggle puzzle board. Every row of the puzzle should have the same number of
   letters. The end-of-file marker in the stream or a blank line in the stream marks the
   end of a puzzle. Returns true if a puzzle is read and can be used for puzzle-solving.
   - solve - This method finds all the words of the dictionary that appear in the puzzle
   grid. Words can start at any entry of the grid and cannot reuse a cell of the grid in
   the same word. This method returns the list of words found (in sorted order) as well
   as the navigational path followed by the word in the Grid in a specific format.
   - print - This method prints the input Boggle Grid.
   Private Methods:
   - isStringOnlyAlphabetic - Method to check that current input string is made up of
   only lowercase alphabetic letters.
   - splitStringByCharSize - Method to split the user input for the Boggle Grid into rows
   for the Grid puzzle.
2. BoggleUtil.java - This contains the assisting methods for finding the Words in a Boggle
   Grid based on a Dictionary fed to the System. It utilizes a Prefix Tree static Class for storing
   the words and for their retrieval.
   This class also uses a static class called PrefixTreeNode – it represents and initializes a
   Prefix Tree to store all the words fed into the dictionary. Later, the Prefix Tree can be
   traversed to find the words.
   This static class has some parameters:
   3
   - leafNode – indicates the end of a word in the Trie.
   - A constructor that creates new nodes with leafNode set to false.
   Public Methods:
   - processGridToFindWords - This method accepts a list of words (dictionary), an
   MxN Matrix of lowercase characters i.e., the Boggle Grid, and the number of rows
   and cols of the grid, and processes that information to report a List of Strings (words
   found in the puzzle), where each string is of the form:
   <word> \t <starting_X> \t <staring_Y> \t <navigation_sequence>
   Private Methods:
   - isSafeToVisit - Method to check that current location coordinate (i, j) is valid i.e.,
   is within the matrix range.
   - insertIntoPrefixTree - Inserts a word into the Prefix Tree.
   - findWordsInDictionary - Method to look for words starting from each individual
   character in the Boggle Grid Cells.
   - searchWordInGrid - Recursively searches for a word from the dictionary fed to the
   system in the Prefix Tree.
   - resolvePaths - Resolves the paths found for each word in the wordsFound Map to a
   Reported Path of specific format.
   - resolvePathsToCoordinates - Resolves Paths to Coordinate class Objects.
   - reportNavigationSequence - Reports the Navigation Sequence String based on the
   path for a Word found in the Boggle Grid.
   - compareCoordinates - This method compares two objects of the class Coordinate
   and returns a directional identifier to indicate which direction in the Grid we have
   to move to go from the first Coordinate object to the second Coordinate object.
3. Coordinate.java – a POJO to store the X & Y Coordinates of the Grid Matrix. It only has
   a constructor that sets both X & Y coordinates to -1 at first.
   
## KEY ALGORITHMS AND DESIGN ELEMENTS
#### Strategy for the Word Insert –
   When we insert a character (part of a key) into a trie, we start from the root node and then search
   for a reference, which corresponds to the first key character of the string whose character we are
   trying to insert in the trie. Two scenarios are possible:
1. A reference exists, if, so then we traverse down the tree following the reference to the
   next children level.
2. A reference does not exist, then we create a new node and refer it with parent's reference
   matching the current key character. We repeat this step until we get to the last character
   of the key, then we mark the current node as an end node, and the algorithm finishes.
   

   Having inserted the words into the trie using the above approach, I then utilized this Trie data
   structure to optimize the search for words in a Boggle Grid using the following algorithm:
1. Create an Empty Trie and insert all words of the given dictionary into Trie. If a word
   already exists, we can set the leaf node to true for that word.
2. After that, we have to pick only those characters in the Boggle Grid which are child nodes
   of the root of Trie.
3. Search a word in a Trie which starts with a character that we pick in step 2.
4. During the recursive search of Trie, track the path taken.
5. Group all the possible paths that form a single word.
6. Select the path that has the smallest X coordinate. In case of a tie, select the smallest Y
   coordinate next.
7. Process the path identified to report the path that forms the word by sequentially calculating
   a navigational path from the first coordinate in the path to the last.
   
This approach is better than say, a brute force search because it limits the state search space to only
   the nodes of the Trie instead of traversing all possible paths.
#### Strategy for the Word Search –
   A key in a trie is stored as a path that starts from the root node and it might go all the way to the
   leaf node or some intermediate node. If we want to search a key in a trie, we start with the root
   node and then traverses downwards if we get a reference match for the next character of the key
   we are searching, then there are two cases:
1. A reference of the next character exists, hence we move downwards following this link,
   and proceed to search for the next key character.
2. A reference does not exist for the next character. If there are no more characters of the
   key present and this character is marked as leafNode = true, then we return true,
   implying that we have found the key.
   
Otherwise, two more cases are possible, and in each of them, we return false. These are:

1. There are key characters left in the key, but we cannot traverse down as the path is
   terminated, hence the key doesn't exist.
2. No characters in the key are left, but the last character is not marked as leafNode = false.
   Therefore, the search key is just the prefix of the key we are trying to search in the trie.
#### Strategy for Boggle Grid Solving -
   - The execution begins with the method to look for words starting from each
   individual Character in the Boggle Grid Cells. We move Cell-by-cell, and the
   internal search is handled by recursive calls to the searchWordInGrid method.
   - The searchWordInGrid is a recursive method to recursively search for all the words
   that are present in the dictionary and also belong in the Boggle Grid by looking for
   the word in the Trie. It keeps track of the Cells visited to avoid re-visiting a cell
   while identifying the path for a word.
   5
   With each recursive call, it stores the current location (i, j) in the grid in a string and
   passes it to the next recursive call and so on until the recursion reaches its end when
   a leafNode = true is found (which represents the end of a word). Once a leafNode
   is found to be true, signifying the end of the path for that word, the method stores
   the Word & its path in a Map. Since it is possible that the same word can be made
   in many ways in the Boggle Grid, we store a list of all the paths for each individual
   word.
   - Having identified each word and its paths, we then convert the paths obtained to
   their equivalent paths in a matrix of the same dimensions as the boggle grid, but
   this new matrix has the X & Y starting coordinates on the lower-left corner, just as
   a regular graph would.
   - Then the paths from this new grid are compared to pick the path with the smallest
   X coordinate and in case of a tie, the smallest Y coordinate.
   - Once a specific path is chosen to provide a word, we then process each location in
   the path, and compare it to its immediately next location in the path to see which
   direction in the grid we are moving, all the while keeping track of the directions by
   using the direction identifiers. These are collected together to give the final
   navigational sequence for the word.
   # Advantages of using Tries:
   - With Trie, we can insert and find strings in O(L) time where L represents the length of a
   single word. This is much faster than a BST.
   - This is also faster than Hashing because of the ways it is implemented. We do not need to
   compute any hash function. No collision handling is required.
   - Each time we add a word’s letter, we know that we’ll only ever have to look at 26 possible
   indexes in a node’s array since there are only 26 possible letters in the English alphabet.
   This limits the state search space for both input & retrieval of the data.
   ## ASSUMPTIONS & LIMITATIONS
   - All input will be lower case.
   - The dictionary of words is provided in sorted order.
   - The inputs for the gid & the dictionary will not have any special characters.
   - A 0*0 Grid is an invalid grid and will throw an exception.
   - In case either the dictionary or the grid is not ready for solving, the program throws errors
   in such cases.
   - The program has sigh space complexity as the Trie structure requires large memory to store
   the keywords. It is a downside of a trie is that it takes up a lot of memory and space with
   empty (null) pointers.
   - When multiple paths for a word start from the same cell, it takes either of the paths after
   applying the smallest X rule.