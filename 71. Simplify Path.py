class Solution:
    def simplifyPath(self, path: str) -> str:
        
        
        stack = []
        arr = path.split("/")
        
        # print(arr)
        for s in arr:
            if s == "..":
                if len(stack) > 0:
                    stack.pop()
            elif s.strip() == "":
                # do nothing.
                pass
            elif s == ".":
                # actually do nothing.
                pass
            elif s == "/":
                # actually do nothing.
                pass
            else:
                # some string is formed.
                stack.append(s)
        
        
        # print(stack)
        # lastly join this string.
        string = "/" + "/".join(stack)
        
        return string