with text_io; use text_io;

procedure string_application is

    s : string(1..20);

    function upshift (c : character) return character is
    begin
      if c >= 'a' and c <= 'z' then
        return character'val(character'pos(c) - character'pos('a') + character'pos('A'));
      else
        return c;
      end if;
    end;

    function downshift (c : character) return character is
    begin
      if c >= 'A' and c <= 'Z' then
        return character'val(character'pos(c) - character'pos('A') + character'pos('a'));
      else
        return c;
      end if;
    end;

    function rot13 (c : character) return character is
    begin
      if (c >= 'A' and c <= 'M') or (c >= 'a' and c <= 'm') then
        return character'val(character'pos(c) + 13);
      elsif (c >= 'N' and c <= 'Z') or (c >= 'n' and c <= 'z') then
        return character'val(character'pos(c) - 13);
      else
        return c;
      end if;
    end;

    generic
        with function ...
    procedure apply (s : in out string);

    procedure apply (s : in out string) is ...

    procedure toupper is new apply...
    procedure tolower is new apply...
    procedure encode is new apply...

begin
  s := "The quick fox jumps!";
  put_line(s);                      -- -> The quick fox jumps!
  toupper(s);
  put_line(s);                      -- -> THE QUICK FOX JUMPS!
  tolower(s);
  put_line(s);                      -- -> the quick fox jumps!
  encode(s);
  put_line(s);                      -- -> gur dhvpx sbk whzcf!
  encode(s);
  put_line(s);                      -- -> the quick fox jumps!
end;
