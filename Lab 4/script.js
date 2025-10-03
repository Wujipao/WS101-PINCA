let players = [];

// Fetch existing players from JSONBin
async function fetchPlayers() {
  console.log("Fetching players from API...");
  try {
    const resp = await fetch("https://api.jsonbin.io/v3/b/68df495ed0ea881f4093858c/latest", {
      headers: {
        "X-Master-Key": "$2a$10$DHuz2usuRds53Xpdpws.3.kjk6Q9.MAhOP1JVcIYd9Fqux1.B3B5W"
      }
    });

    if (!resp.ok) throw new Error(`HTTP error: ${resp.status}`);

    const data = await resp.json();
    players = data.record; 
    console.log("Players fetched:", players);
  } catch (err) {
    console.error("Error fetching players:", err);
  }
}


async function addPlayer(name, position, games, score) {
  const newPlayer = { name, position, games, score };
  players.push(newPlayer);
  console.log("Player added:", newPlayer);

  try {
    const resp = await fetch("https://api.jsonbin.io/v3/b/68df495ed0ea881f4093858c", {
      method: "PUT", 
      headers: { 
        "Content-Type": "application/json",
        "X-Master-Key": "$2a$10$DHuz2usuRds53Xpdpws.3.kjk6Q9.MAhOP1JVcIYd9Fqux1.B3B5W"
      },
      body: JSON.stringify(players),
    });
    const result = await resp.json();
    console.log("Updated bin:", result);
  } catch (err) {
    console.error("Failed to persist new player:", err);
  }
}

//Calculate the Average Score
function average(){
    if (players.length == 0){
        console.log("No players available.");
        return;
    }
    const total = players.reduce((sum, p) => sum + p.score, 0);
    const avg = (total / players.length).toFixed(2);
    console.log("Average Score: ", avg);
}

//Filter Player by position
function filterByPosition(position){
    const filter=players.filter(p => p.position === position);
    console.log("Players in position: ", filter);
}

//Find the highest score
function highestScore(){
    if (players.length == 0){
        console.log("No players available");
    }
    const highest = players.reduce((max, p) => p.score > max.score ? p : max, players[0]);
    console.log("Highest Score: ", highest);
}

//Games Played
function gamesPlayed(){
    const groups = {
        "0-10": players.filter(p => p.games <= 10),
        "11-20": players.filter(p => p.games > 10 && p.games<= 20),
        "21+": players.filter(p => p.games > 20)
    };
    console.log("Games Played: ", groups);
}
async function main() {
    await fetchPlayers();
    average();
    filterByPosition("Middle Blocker");
    highestScore();
    gamesPlayed();
    await addPlayer("Khristher", "Swimmer", 99, 100);
    average();
}

main();