# https://www.algoexpert.io/questions/tournament-winner

from typing import List

HOME_WINS = 1


def tournamentWinner(competitions: List[str], results: List[int]) -> str:
    tournamentWinner = competitions[0][0]
    scores = {tournamentWinner: 0}

    for i in range(len(competitions)):
        [home, away] = competitions[i]
        winner = home if results[i] == HOME_WINS else away

        if winner not in scores:
            scores[winner] = 0

        scores[winner] += 3

        if scores[winner] > scores[tournamentWinner]:
            tournamentWinner = winner

    return tournamentWinner


print(
    tournamentWinner([["HTML", "C#"], ["C#", "Python"], ["Python", "HTML"]], [0, 0, 1])
)
