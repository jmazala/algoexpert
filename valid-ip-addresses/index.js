// https://www.algoexpert.io/questions/valid-ip-addresses

// Trying all combinations recursively
function validIPAddresses(string) {
  const output = [];
  helper("", string);
  return output;

  function helper(prefix, remaining) {
    if (!remaining.length) {
      output.push(prefix.slice(prefix, prefix.length - 1));
      return;
    }

    const reps = Math.min(3, remaining.length);
    for (let i = 0; i < reps; i++) {
      prefix += remaining[0];
      remaining = remaining.slice(1);
      if (isValidPrefix(prefix, remaining)) {
        helper(prefix.slice() + '.', remaining);
      }
    }
  }
}

function isValidPrefix(prefix, remaining) {
  const sections = prefix.split('.');

  if (sections.length > 4 || sections.length === 4 && remaining.length > 0 || sections.length < 4 && remaining.length === 0) {
    return false;
  }

  for (const section of prefix.split('.')) {
    if (section[0] === '0' && section.length > 1) {
      return false;
    }

    const number = Number(section);
    if (number < 0 || number > 255) {
      return false;
    }
  }

  return true;
}

function validIPAddresses2(string) {
  const output = [];

  // PART 1
  for (let i = 0; i < Math.min(string.length, 4); i++) {
    const parts = new Array(4);
    parts[0] = string.slice(0, i);
    if (!isValidPart(parts[0])) {
      continue;
    }

    // PART 2
    for (let j = i + 1; j < i + Math.min(string.length - i, 4); j++) {
      parts[1] = string.slice(i, j);
      if (!isValidPart(parts[1])) {
        continue;
      }

      // PART 3
      for (let k = j + 1; k < j + Math.min(string.length - j, 4); k++) {
        parts[2] = string.slice(j, k);

        if (!isValidPart(parts[2])) {
          continue;
        }

        // PART 4
        parts[3] = string.slice(k);
        if (!isValidPart(parts[3])) {
          continue;
        }

        output.push(parts.join('.'));
      }
    }
  }

  return output;
}

function isValidPart(part) {
  const asInt = parseInt(part);
  return asInt <= 255 && asInt.toString().length === part.length;
}

// Do not edit the line below.
exports.validIPAddresses = validIPAddresses;

console.log(validIPAddresses("1921680"));
console.log(validIPAddresses2("1921680"));
