# https://www.algoexpert.io/questions/generate-div-tags


def helper(openingNeeded, closingNeeded, prefix, output):
    if openingNeeded == 0 and closingNeeded == 0:
        output.insert(0, prefix)
        return

    if openingNeeded > 0:
        helper(openingNeeded - 1, closingNeeded, prefix + "<div>", output)

    if closingNeeded > openingNeeded:
        helper(openingNeeded, closingNeeded - 1, prefix + "</div>", output)


def generateDivTags(numberOfTags):
    output = []
    helper(numberOfTags, numberOfTags, "", output)
    return output
