# https://www.algoexpert.io/questions/shorten-path


def shortenPath(path: str) -> str:
    is_absolute_path = path[0] == "/"
    parts = [part for part in path.split("/") if part != "." and part != ""]
    stack = [""] if is_absolute_path else []

    for part in parts:
        if part != "..":
            stack.append(part)
            continue

        if len(stack) == 0 or stack[-1] == "..":
            stack.append(part)
        elif stack[-1] != "":
            stack.pop()

    return "/" if len(stack) == 1 and stack[0] == "" else "/".join(stack)


print(shortenPath("/../../foo/bar/baz"))  # /foo/bar/baz
print(shortenPath("../a/b/../../c/d/../e/../../f/.."))  # ..
print(shortenPath("/a/.."))  # /
print(shortenPath("/foo/../test/../test/../foo//bar/./baz"))  # /foo/bar/baz
