### Unit Tesing Using TDD Approach
### Note:
* Write a fail test case
* Write a minimum a code to pass the test case
* Implement rest of the code

### Test Cases:
> For example, let us say we have these three use cases in the hangman game. We will pick the 'Play
    next round' use case as our first use case.Because it has the core game logic while the other two are
    dependent on it. Now we elaborate this use cases by writing down its flow. Let us say this is the first
    draft of the first basic flow of Play next round use case. When we start a new round, the game saves a
    previous score if any, initializes its current score to zero and the remaining trials to say 10. It fetches
    the next word for next round. Let us say the game fetches the word pizza. This makes the clue a string
    of five dashes. The game prompts the player to enter a first guessand the player enters 'a'. The game
    checks if the guess is right and so on. Now let us look at the acceptance criteria. The player expects
    that the word never repeats, the word is not predictable, the score is saved somewhere and so
    on. These criteria make you think about where are the words coming from and where are we storing
    the score. As the word sourceand score database are external dependencies, they become good
    candidates to start out first set of test cases. Our first set of test cases can then be to test the first
    acceptance criteria that a word fetched by hangman in each round is not repeated or that it is
    unique.Let's look at the input side which is word uniqueness. - So fetch word should return a unique
    word every time. - That means we need to store previous words somewhere in out test file to compare
    with. How about we use a hash set for that? - But how many words can we store in that? - Actually
    that would depend on how many words are available from the word source, right? - Right but that we
    won't know yet. Why don't we just start with an arbitrary number? Say one hundred. - So I'll create the
    next case as test_uniquenessOfFetchedWord.We need a randomizer to get a random number. And int
    variable requested length. That will store the random number and a set to store used words. Since
    we're testing for uniqueness within 100 rounds, I will add another variable round that will count for one
    hundred rounds.Then a string variable to store the word and an instance of hangman, now in a loop,
    well round is less than one hundred. Get a random number assigned to requested length between five
    and ten. Fetch the word of the requested length into the word variable and then increment the round
    number. Finally, assert true that the word is added to the used word set. If it returns true, it means that
    it was not already in the set. And if I run the test now it fails. - All right, now I'm going to go to pass the
    test case. I have already copied our sample word source in the project folder. And since the method
    should not return and duplicate, it will need its own hash set to check for duplicates. So I define the
    same variable here as well as used word set at the class level. Now in the fetch word method, I will
    have to remove this faking logic. I first create a result variable as a string then I open a try block to
    read the file,using buffered reader wrapping a file reader to open WordSource.txt, except the
    suggestion to create the catch block for exceptions. Now inside the try block, I open a while loop to
    result from the buffered reader inside which I read the first word from the file then check if the result's
    length is not equal to the required length. I continue to loop to read the next word. Else I add it to the
    used word set. If it returns true then I break because it means it is unique. Still loop continues, outside
    the loop I return the results, run the test. Ah, the gorgeous screen again.
