function shortenPath(path) {
  const isAbsolutePath = path[0] === '/';
  const tokens = path.split('/').filter(i => i !== '.' && i !== '');
  const stack = [];

  if (isAbsolutePath) {
    stack.push('');
  }

  for (const token of tokens) {
    if (token !== '..') {
      stack.push(token);
      continue;
    }

    //if the stack is empty or last element is a '..', add another '/..'
    if (stack.length === 0 || stack[stack.length - 1] === '..') {
      stack.push(token);
      continue;
    }

    //otherwise pop anything meaningful off the stack
    if (stack[stack.length - 1] !== '') {
      stack.pop();
    }
  }

  if (stack.length === 1 && stack[0] === '') {
    return '/';
  }

  return stack.join('/');
}

// Do not edit the line below.
exports.shortenPath = shortenPath;
