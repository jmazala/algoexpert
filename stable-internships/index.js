function stableInternships(interns, teams) {
  const numberOfInterns = interns.length;
  const choiceNumberPerIntern = Array(numberOfInterns).fill(0);
  const matchedInterns = Array(numberOfInterns).fill(null);

  const availableInterns = [];
  for (let i = 0; i < numberOfInterns; i++) {
    availableInterns.push(i);
  }

  while (availableInterns.length) {
    const currentIntern = availableInterns.shift();
    const teamToMatch = interns[currentIntern][choiceNumberPerIntern[currentIntern]];
    choiceNumberPerIntern[currentIntern] += 1;

    if (matchedInterns[teamToMatch] === null) {
      matchedInterns[teamToMatch] = currentIntern;
      continue;
    }

    const teamsIntern = matchedInterns[teamToMatch];
    if (teams[teamToMatch].indexOf(currentIntern) < teams[teamToMatch].indexOf(teamsIntern)) {
      matchedInterns[teamToMatch] = currentIntern;
      availableInterns.push(teamsIntern);
    } else {
      availableInterns.push(currentIntern);
    }
  }

  return matchedInterns.map(function (intern, team) {
    return [intern, team];
  });

  return matchedInterns;
}

// Do not edit the line below.
exports.stableInternships = stableInternships;

console.log(JSON.stringify(stableInternships([[0, 1, 2], [1, 0, 2], [1, 2, 0]], [[2, 1, 0], [1, 2, 0], [0, 2, 1]])));