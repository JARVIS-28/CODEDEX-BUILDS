// Write code below 💖

const words = ["javascript", "programming", "hangman", "developer", "function"];
const chosenWord = words[Math.floor(Math.random() * words.length)];
let guessedLetters = new Set();
let remainingAttempts = 6;

function displayWord() {
    return chosenWord.split('').map(letter => guessedLetters.has(letter) ? letter : '_').join('');
}

function playGame() {
    console.log("Welcome to Hangman!");
    console.log(displayWord().split('').join(' '));

    const readline = require('readline');
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    function askForLetter() {
        rl.question('Guess a letter: ', (letter) => {
            const guessedLetter = letter.toLowerCase().trim();

            if (!guessedLetter.match(/^[a-z]$/)) {
                console.log('Please enter a valid letter.');
            } else if (guessedLetters.has(guessedLetter)) {
                console.log('You already guessed that letter!');
            } else {
                guessedLetters.add(guessedLetter);
                if (!chosenWord.includes(guessedLetter)) {
                    remainingAttempts--;
                    console.log(`Incorrect guess! Remaining attempts: ${remainingAttempts}`);
                }
            }

            console.log(displayWord().split('').join(' '));

            if (!displayWord().includes('_')) {
                console.log('Congratulations! You guessed the word!');
                rl.close();
            } else if (remainingAttempts <= 0) {
                console.log(`Game over! The word was: ${chosenWord}`);
                rl.close();
            } else {
                askForLetter();
            }
        });
    }

    askForLetter();
}

playGame();
