DIM year as Integer

'' DIM divisibleBy4 = (year Mod 4) = 0

' 400 diye divisible
'


IF idx = 0 THEN 
	price = "-"
ELSE IF idx = 1 THEN
	price = "1"


price = "$ " & price



DIM msg as String

IF (year Mod 400 = 0) Then

	msg = "Leap Year"

ELSEIF (year Mod 4 = 0) AND (year Mod 100 <> 0) Then

	msg = "Leap Year"

ELSE

	msg = "Normal Year"

END IF

