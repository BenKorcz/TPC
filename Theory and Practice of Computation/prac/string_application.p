program string_application (output);

  type
    string20 = packed array [1 .. 20] of char;

  var
    s: string20;

  function upshift (c : char) : char;
  begin
    if (c >= 'a') and (c <= 'z') then
      upshift := chr(ord(c) - ord('a') + ord('A'))
    else
      upshift := c;
  end;

  function downshift (c : char) : char;
  begin
    if (c >= 'A') and (c <= 'Z') then
      downshift := chr(ord(c) - ord('A') + ord('a'))
    else
      downshift := c;
  end;

  function rot13 (c : char) : char;
  begin
    if (c >= 'A') and (c <= 'M') or (c >= 'a') and (c <= 'm') then
      rot13 := chr(ord(c) + 13)
    else if (c >= 'N') and (c <= 'Z') or (c >= 'n') and (c <= 'z') then
      rot13 := chr(ord(c) - 13)
    else
      rot13 := c;
  end;

  procedure apply (function map(c : char) : char; var s : string20);
  var
    i : integer;
  begin
    for i := 1 to 20 do
      s[i] := map(s[i]);
  end;

begin
  s := 'The quick fox jumps!';

  writeln(s);                      (* -> The quick fox jumps! *)
  apply(upshift, s);
  writeln(s);                      (* -> THE QUICK FOX JUMPS! *)
  apply(downshift, s);
  writeln(s);                      (* -> the quick fox jumps! *)
  apply(rot13, s);
  writeln(s);                      (* -> gur dhvpx sbk whzcf! *)
  apply(rot13, s);
  writeln(s);                      (* -> the quick fox jumps! *)
end.
