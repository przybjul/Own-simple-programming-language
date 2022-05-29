read x
d = 2.2
w = 1.1
c = 2

function int f
  read b
  loop b
    c = c + 1
  endloop
  if x < 5 then
    f = c + x
  endif
  if x == 5 then
  loop b
    f = c * x
  endloop
  endif
  if x > 5 then
    f = x - c
  endif
  x = 5
  write x
endfunction
c=1
j = 3
loop j
if j == 2 then
loop j
    c = c + 1
endloop
endif
if j!=2 then
c = c*10
endif
endloop

z = f
c = 1
write c
write z


function real e
  t = 22.3
  write t
  d = d + w
  write d
  d = w + 1.1
  e = d + w
endfunction

t = e

write t
